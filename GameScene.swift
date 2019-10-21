//
//  GameScene.swift
//  Void_Protector
//
//  Created by Sneh Patel on 12/27/17.
//  Copyright © 2017 AmDa LLC. All rights reserved.
//

import SpriteKit
import GameplayKit
var gameScore = 0
var bulletlives = 5

let bulletLable = SKLabelNode(fontNamed: "The Bold Font")
func setBulletLives(){
    bulletlives = 5
    bulletLable.text = "Ammo: \(bulletlives)"
}
class GameScene: SKScene, SKPhysicsContactDelegate {
    
    let scoreLable = SKLabelNode(fontNamed: "The Bold Font")
    
    var livesNumber = 3
    let livesLable = SKLabelNode(fontNamed: "The Bold Font")
    
    
    var livesSTasteroids = 2
    
    var livesBoss = 5
    
    var levelNumber = 0
    
   
    
    let tapToStartLable = SKLabelNode(fontNamed: "The Bold Font")
    
    let TitleLable = SKLabelNode(fontNamed: "The Bold Font")
    
    enum gameState{
        case preGame //when the game state is before the game
        case inGame //when the game state is during the game
        case postGame //when the game state is after the game
    }
    var currentGameState = gameState.preGame

    var player = SKSpriteNode()
let boss = SKSpriteNode(imageNamed: "Boss")
let Enemybullet = SKSpriteNode(imageNamed: "enemy_bullet")
let bulletSound = SKAction.playSoundFileNamed("LaserSound.wav", waitForCompletion: false)
let EnemybulletSound = SKAction.playSoundFileNamed("LaserSound.wav", waitForCompletion: false)
let explosionSound = SKAction.playSoundFileNamed("Explosion.wav", waitForCompletion: false)
let ammoPickupSound = SKAction.playSoundFileNamed("AmmoPickup.mp3", waitForCompletion: false)
let FireButton = SKSpriteNode(imageNamed: "Fire_Button")
let asteroid = SKSpriteNode(imageNamed: "Asteroid")
let asteroidST = SKSpriteNode(imageNamed: "StrongAsteroid")
let asteroidFNCY = SKSpriteNode(imageNamed: "FancyAsteroid")
let asteroidHD = SKSpriteNode(imageNamed: "AsteroidHard")
var TextureAtlas = SKTextureAtlas()
var TextureArray = [SKTexture]()
    
    struct PhysicsCategories{
        static let None : UInt32 = 0 //binary for 0
        static let Player : UInt32 = 0b1 //binary for 1
        static let Bullet : UInt32 = 0b10 //binary for 2
        static let EnemyBullet : UInt32 = 0b100 //binary for 4
        static let Asteroid : UInt32 = 0b101 //binary for 5
        static let StrongAsteroid : UInt32 = 0b110 //binary for 6
        static let FancyAsteroid : UInt32 = 0b111 //binary for 7
        static let HardAsteroid : UInt32 = 0b1000 //binary for 8
        static let Boss : UInt32 = 0b1001 //bianry for 9
        static let AmmoCrate : UInt32 = 0b1010 //binary for 10
        static let ExtraLife : UInt32 = 0b1011 //binary for 11
        
    }

    func random() -> CGFloat {
        return CGFloat(Float(arc4random()) / 0xFFFFFFFF)
    }
    func random(min: CGFloat, max:CGFloat) -> CGFloat {
        return random() * (max - min) + min
    }

    var gameArea: CGRect
    
    override init(size: CGSize) {
        
        let maxAspectRatio: CGFloat = 16.0/9.0
        let playableWidth = size.height / maxAspectRatio
        let margin = (size.width - playableWidth) / 2
        gameArea = CGRect(x: margin, y: 0, width: playableWidth, height: size.height)
        
        super.init(size: size)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func didMove(to view: SKView) {
        
        gameScore = 0
        
        self.physicsWorld.contactDelegate = self
        
        for i in 0...1{
            let background = SKSpriteNode(imageNamed: "background")
            background.size = self.size
            background.anchorPoint = CGPoint(x: 0.5, y: 0)
            background.position = CGPoint(x: self.size.width/2, y: self.size.height * CGFloat(i))
            background.zPosition = 0
            background.name = "Background"
            self.addChild(background)
        }
        
        TextureAtlas = SKTextureAtlas(named: "PlayerShip")

        
        TextureArray.append(SKTexture(imageNamed: "PlayerShip"))
        
        
        player = SKSpriteNode(imageNamed: TextureAtlas.textureNames[0])
        player.setScale(0.75)
        player.position = CGPoint(x: self.size.width/2, y: 0 - player.size.height)
        player.zPosition = 2
        player.physicsBody = SKPhysicsBody(texture: player.texture!, size: player.size)
        player.physicsBody!.affectedByGravity = false
        player.physicsBody!.categoryBitMask = PhysicsCategories.Player
        player.physicsBody!.collisionBitMask = PhysicsCategories.None
        player.physicsBody!.contactTestBitMask = PhysicsCategories.Asteroid | PhysicsCategories.Boss
        self.addChild(player)
        
        FireButton.setScale(0.5)
        FireButton.position = CGPoint(x: self.size.width * 0.8, y: FireButton.frame.size.height - self.size.height)
        FireButton.zPosition = 100
        self.addChild(FireButton)
        
        scoreLable.text = "Score = 0"
        scoreLable.fontSize = 70
        scoreLable.fontColor = SKColor.lightGray
        scoreLable.horizontalAlignmentMode = SKLabelHorizontalAlignmentMode.left
        scoreLable.position = CGPoint(x: self.size.width * 0.15, y: self.size.height + scoreLable.frame.size.height)
        scoreLable.zPosition = 100
        self.addChild(scoreLable)
        
        livesLable.text = "Lives = 3"
        livesLable.fontSize = 70
        livesLable.fontColor = SKColor.lightGray
        livesLable.horizontalAlignmentMode = SKLabelHorizontalAlignmentMode.right
        livesLable.position = CGPoint(x: self.size.width * 0.85, y: self.size.height + livesLable.frame.size.height)
        livesLable.zPosition = 100
        self.addChild(livesLable)
        
        bulletLable.text = "Ammo: \(bulletlives)"
        bulletLable.fontSize = 45
        bulletLable.fontColor = SKColor.lightGray
        bulletLable.horizontalAlignmentMode = SKLabelHorizontalAlignmentMode.right
        bulletLable.position = CGPoint(x: self.size.width * 0.28, y: bulletLable.frame.size.height - self.size.height)
        bulletLable.zPosition = 100
        self.addChild(bulletLable)
        
        let moveTopOnToScreenAction = SKAction.moveTo(y: self.size.height * 0.95, duration: 0.5)
        let moveBottomOnToScreenAction = SKAction.moveTo(y: self.size.height * 0.03, duration: 0.5)
        let moveBottomOnToScreenButtonAction = SKAction.moveTo(y: self.size.height * 0.1, duration: 0.5)
        scoreLable.run(moveTopOnToScreenAction)
        livesLable.run(moveTopOnToScreenAction)
        bulletLable.run(moveBottomOnToScreenAction)
        FireButton.run(moveBottomOnToScreenButtonAction)
        
        tapToStartLable.text = "Tap To Begin"
        tapToStartLable.fontSize = 100
        tapToStartLable.fontColor = SKColor.lightGray
        tapToStartLable.position = CGPoint(x: self.size.width/2, y: self.size.height * 0.5)
        tapToStartLable.zPosition = 1
        tapToStartLable.alpha = 0
        self.addChild(tapToStartLable)
        
        let fadeInAction = SKAction.fadeIn(withDuration: 0.5)
        tapToStartLable.run(fadeInAction)
        
       
        
    }
    
    var lastUpdateTime: TimeInterval = 0
    var deltaFrameTime: TimeInterval = 0
    var amountToMovePerSecond: CGFloat = 600.0
    
    override func update(_ currentTime: TimeInterval) {
        
        if lastUpdateTime == 0{
            lastUpdateTime = currentTime
        }
        else{
            deltaFrameTime = currentTime - lastUpdateTime
            lastUpdateTime = currentTime
        }
        
        let amountToMoveBackground = amountToMovePerSecond * CGFloat(deltaFrameTime)
        
        self.enumerateChildNodes(withName: "Background"){
            background, stop in
            
            if self.currentGameState == gameState.inGame{
                background.position.y -= amountToMoveBackground
            }
            
            if background.position.y < -self.size.height{
                background.position.y += self.size.height * 2
            }
        }
    }
    
    func startGame(){
        
        currentGameState = gameState.inGame
        
        let fadeOutAction = SKAction.fadeOut(withDuration: 0.5)
        let deleteAction = SKAction.removeFromParent()
        let deleteSequence = SKAction.sequence([fadeOutAction, deleteAction])
        tapToStartLable.run(deleteSequence)
        
        let movePlayerOnToScreenAction = SKAction.moveTo(y: self.size.height * 0.2, duration: 0.75)
        let waitToStartGame = SKAction.wait(forDuration: 1.0)
        let startLevelAction = SKAction.run(startNewLevel)
        let startLevelSequence = SKAction.sequence([waitToStartGame, startLevelAction])
        player.run(movePlayerOnToScreenAction)
        let waitToStartGif = SKAction.wait(forDuration: 1.0)
        player.run(SKAction.repeatForever(SKAction.animate(with: TextureArray, timePerFrame: 0.3)))
        //let StartGifSequence = SKAction.sequence([waitToStartGif, StartPlayerGif])
        self.run(startLevelSequence)

    }
    
    func runGameOver(){
        
        currentGameState = gameState.postGame
        
        self.removeAllActions()
        boss.removeAllActions()
        self.enumerateChildNodes(withName: "Bullet"){
            bullet, stop in
            bullet.removeAllActions()
        }
        self.enumerateChildNodes(withName: "asteroid"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        self.enumerateChildNodes(withName: "STasteroid"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        self.enumerateChildNodes(withName: "FNCYasteroid"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        self.enumerateChildNodes(withName: "HDasteroid"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        self.enumerateChildNodes(withName: "Ammo"){
            ammo, stop in
            ammo.removeAllActions()
        }
        self.enumerateChildNodes(withName: "ExtraLife"){
            ExtraLife, stop in
            ExtraLife.removeAllActions()
        }
        
        let changeSceneAction = SKAction.run(changeSceneToGameOver)
        let waitToChangeScene = SKAction.wait(forDuration: 1.0)
        let changeSceneSequence = SKAction.sequence([waitToChangeScene, changeSceneAction])
        self.run(changeSceneSequence)
        
    }
    
    func changeSceneToGameOver(){
        
        let sceneToMoveTo = GameOverScene(size: self.size)
        sceneToMoveTo.scaleMode = self.scaleMode
        let mytransition = SKTransition.fade(withDuration: 0.5)
        self.view!.presentScene(sceneToMoveTo, transition: mytransition)
    }
    
    func changeSceneToGameWon(){
        
        let sceneToMoveTo = GameWonScene(size: self.size)
        sceneToMoveTo.scaleMode = self.scaleMode
        let mytransition = SKTransition.fade(withDuration: 0.5)
        self.view!.presentScene(sceneToMoveTo, transition: mytransition)
    }
    
    func runYouWon(){
        
        currentGameState = gameState.postGame
        
        self.removeAllActions()
        boss.removeAllActions()
        self.enumerateChildNodes(withName: "Bullet"){
            bullet, stop in
            bullet.removeAllActions()
        }
        self.enumerateChildNodes(withName: "Asteroid"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        self.enumerateChildNodes(withName: "AsteroidST"){
            asteroid, stop in
            asteroid.removeAllActions()
        }
        
        let changeSceneAction = SKAction.run(changeSceneToGameWon)
        let waitToChangeScene = SKAction.wait(forDuration: 1.0)
        let changeSceneSequence = SKAction.sequence([waitToChangeScene, changeSceneAction])
        self.run(changeSceneSequence)
        
    }
    
    func deleteSTasteroid(){

    }
    
    
    func loseALife(){
        livesNumber -= 1
        livesLable.text = "Lives = \(livesNumber)"
        
        let scaleUp = SKAction.scale(to: 1.5, duration: 0.2)
        let scaleDown = SKAction.scale(to: 1, duration: 0.2)
        let scaleSequence = SKAction.sequence([scaleUp, scaleDown])
        livesLable.run(scaleSequence)
        
        if livesNumber == 0{
            runGameOver()
        }
    }
    
    func loseAlifeSTasteroid(){
        
        livesSTasteroids -= 1
    }
    
    func loseAlifeBoss(){
        livesBoss -= 1
        
        if livesBoss == 0{
            addScore()
            runYouWon()
        }
    }
    
    func loseABulletLife(){
        bulletlives -= 1
        bulletLable.text = "Ammo: \(bulletlives)"
        
        let scaleUp = SKAction.scale(to: 1.25, duration: 0.2)
        let scaleDown = SKAction.scale(to: 1, duration: 0.2)
        let scaleSequence = SKAction.sequence([scaleUp, scaleDown])
        bulletLable.run(scaleSequence)
    
    }
    
    
    
    func addScore(){
        gameScore += 1
        scoreLable.text = "Score = \(gameScore)"
        
        if gameScore == 15 || gameScore == 25 || gameScore == 35 || gameScore == 45{
            startNewLevel()
        }
    }
    
    func addScoreST(){
        gameScore += 2
        scoreLable.text = "Score = \(gameScore)"
        
        if gameScore == 15 || gameScore == 25 || gameScore == 35 || gameScore == 45{
            startNewLevel()
        }
    }
    
    func addScoreFNCY(){
        gameScore += 3
        scoreLable.text = "Score = \(gameScore)"
        
        if gameScore == 15 || gameScore == 25 || gameScore == 35 || gameScore == 45{
            startNewLevel()
        }
    }
    
    func addScoreHD(){
        gameScore += 4
        scoreLable.text = "Score = \(gameScore)"
        
        if gameScore == 15 || gameScore == 25 || gameScore == 35 || gameScore == 45{
            startNewLevel()
        }
    }
    
    func addAmmo(){
        bulletlives += 5
        bulletLable.text = "Ammo: \(bulletlives)"
        
        let scaleUp = SKAction.scale(to: 1.25, duration: 0.2)
        let scaleDown = SKAction.scale(to: 1, duration: 0.2)
        let scaleSequence = SKAction.sequence([scaleUp, scaleDown])
        bulletLable.run(scaleSequence)
    }
    
    func addLife(){
        livesNumber += 1
        livesLable.text = "Lives = \(livesNumber)"
        
        let scaleUp = SKAction.scale(to: 1.25, duration: 0.2)
        let scaleDown = SKAction.scale(to: 1, duration: 0.2)
        let scaleSequence = SKAction.sequence([scaleUp, scaleDown])
        livesLable.run(scaleSequence)
    }
    
    
    func didBegin(_ contact: SKPhysicsContact) {
        var body1 = SKPhysicsBody()
        var body2 = SKPhysicsBody()
        
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask{
            body1 = contact.bodyA
            body2 = contact.bodyB
        }
        else{
            body1 = contact.bodyB
            body2 = contact.bodyA
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.Asteroid{
            //if the player has hit the asteroid
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.Asteroid{
            //if the bullet has hit the asteroid
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        self.run(explosionSound)
        addScore()
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.Boss{
            //if the player has hit the boss
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.Boss{
            //if the bullet has hit the boss
        body1.node?.removeFromParent()
        self.run(explosionSound)
        loseAlifeBoss()
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.EnemyBullet{
            //if the enemybullet hits the player
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.StrongAsteroid{
            //if the player hits the strong asteroid
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.StrongAsteroid{
            //if the bullet hits the strong asteroid
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        self.run(explosionSound)
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.FancyAsteroid{
            //if the player hits the fancy asteroid
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.FancyAsteroid{
            //if the bullet hits the fancy asteroid
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        self.run(explosionSound)
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.HardAsteroid{
            //if the player hits the hard asteroid
        body2.node?.removeFromParent()
        self.run(explosionSound)
        loseALife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.HardAsteroid{
            //if the bullet hits the hard asteroid
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        self.run(explosionSound)
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.AmmoCrate{
            //if the player hits the ammo crate
        body2.node?.removeFromParent()
        self.run(ammoPickupSound)
        addAmmo()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.AmmoCrate{
            //if the bullet hits the ammo crate
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        self.run(ammoPickupSound)
        }
        if body1.categoryBitMask == PhysicsCategories.Player && body2.categoryBitMask == PhysicsCategories.ExtraLife{
            //if the player hits the extra life
        body2.node?.removeFromParent()
        addLife()
        }
        if body1.categoryBitMask == PhysicsCategories.Bullet && body2.categoryBitMask == PhysicsCategories.ExtraLife{
            //if the bullet hits the extra life
        body1.node?.removeFromParent()
        body2.node?.removeFromParent()
        }
    }
    
    func startNewLevel(){
        
        levelNumber += 1
        
        let spawnAC = SKAction.run(spawnAmmoCrate)
        let waitToSpawnAC = SKAction.wait(forDuration: 30.0)
        let spawnSequenceAC = SKAction.sequence([waitToSpawnAC, spawnAC])
        let spawnSequenceForeverAC = SKAction.repeatForever(spawnSequenceAC)
        self.run(spawnSequenceForeverAC)
        
        let spawnLife = SKAction.run(spawnExtraLife)
        let waitToSpawnLife = SKAction.wait(forDuration: 45.0)
        let spawnSequenceLife = SKAction.sequence([waitToSpawnLife, spawnLife])
        let spawnSequenceForeverLife = SKAction.repeatForever(spawnSequenceLife)
        self.run(spawnSequenceForeverLife)
        
        if self.action(forKey: "spawningAsteroids") != nil{
            self.removeAction(forKey: "spawningAsteroids")
        }
        
        if self.action(forKey: "spawningAsteroidsSB") != nil{
            self.removeAction(forKey: "spawningAsteroidsSB")
        }
        
        if self.action(forKey: "spawningAsteroidsST") != nil{
            self.removeAction(forKey: "spawningAsteroidsST")
        }
        
        if self.action(forKey: "spawningBoss") != nil{
            self.removeAction(forKey: "spawningBoss")
        }
        if self.action(forKey: "spawningAmmo") != nil{
            self.removeAction(forKey: "spawningAmmo")
        }
        if self.action(forKey: "spawingBullets") != nil{
            self.removeAction(forKey: "spawningBullets")
        }
        
        switch levelNumber {
        case 1:
            let spawn = SKAction.run(spawnAsteroid)
            let waitToSpawn = SKAction.wait(forDuration: 1.0)
            let spawnSequence = SKAction.sequence([waitToSpawn, spawn])
            let spawnForever = SKAction.repeatForever(spawnSequence)
            self.run(spawnForever, withKey: "spawningAsteroids")
            //let spawnAC = SKAction.run(spawnAmmoCrate)
            //let spawnForeverAC = SKAction.repeatForever(spawnAC)
            //self.run(spawnForeverAC, withKey: "spawningAmmo")
            let spawnBullet = SKAction.run(fireEnemyBulletAsteroid)
            let waitToSpawnBullet = SKAction.wait(forDuration: 5.0)
            let spawnSequenceBullet = SKAction.sequence([waitToSpawnBullet, spawnBullet])
            let spawnForeverBullet = SKAction.repeatForever(spawnSequenceBullet)
            self.run(spawnForeverBullet, withKey: "spawingBullets")
        case 2:
            let spawn = SKAction.run(spawnAsteroid)
            let waitToSpawn = SKAction.wait(forDuration: 0.85)
            let spawnSequence = SKAction.sequence([waitToSpawn, spawn])
            let spawnForever = SKAction.repeatForever(spawnSequence)
            self.run(spawnForever, withKey: "spawningAsteroids")
            //let spawnST = SKAction.run(spawnAsteroidStrong)
            //let waitToSpawnST = SKAction.wait(forDuration: 1.35)
            //let spawnSequenceST = SKAction.sequence([waitToSpawnST, spawnST])
            //let spawnForeverST = SKAction.repeatForever(spawnSequenceST)
            //self.run(spawnForeverST, withKey: "spawningAsteroidsST")`
            //let spawnAC = SKAction.run(spawnAmmoCrate)
            //let spawnForeverAC = SKAction.repeatForever(spawnAC)
            //self.run(spawnForeverAC, withKey: "spawningAmmo")
            let spawnBullet = SKAction.run(fireEnemyBulletAsteroid)
            let waitToSpawnBullet = SKAction.wait(forDuration: 5.0)
            let spawnSequenceBullet = SKAction.sequence([waitToSpawnBullet, spawnBullet])
            let spawnForeverBullet = SKAction.repeatForever(spawnSequenceBullet)
            self.run(spawnForeverBullet, withKey: "spawingBullets")
            //let spawnBulletST = SKAction.run(fireEnemyBulletAsteroidST)
            //let waitToSpawnBulletST = SKAction.wait(forDuration: 4.5)
            //let spawnSequenceBulletST = SKAction.sequence([waitToSpawnBulletST, spawnBulletST])
            //let spawnForeverBulletST = SKAction.repeatForever(spawnSequenceBulletST)
            //self.run(spawnForeverBulletST, withKey: "spawingBulletsST")
        case 3:
            let spawn = SKAction.run(spawnAsteroid)
            let waitToSpawn = SKAction.wait(forDuration: 0.75)
            let spawnSequence = SKAction.sequence([waitToSpawn, spawn])
            let spawnForever = SKAction.repeatForever(spawnSequence)
            self.run(spawnForever, withKey: "spawningAsteroids")
            //let spawnST = SKAction.run(spawnAsteroidStrong)
            //let waitToSpawnST = SKAction.wait(forDuration: 1.35)
            //let spawnSequenceST = SKAction.sequence([waitToSpawnST, spawnST])
            //let spawnForeverST = SKAction.repeatForever(spawnSequenceST)
            //self.run(spawnForeverST, withKey: "spawningAsteroidsST")
            //let spawnFNCY = SKAction.run(spawnAsteroidFancy)
            //let waitToSpawnFNCY = SKAction.wait(forDuration: 1.5)
            //let spawnSequenceFNCY = SKAction.sequence([waitToSpawnFNCY, spawnFNCY])
            //let spawnForeverFNCY = SKAction.repeatForever(spawnSequenceFNCY)
            //self.run(spawnForeverFNCY, withKey: "spawningAsteroidsFNCY")
            //let spawnAC = SKAction.run(spawnAmmoCrate)
            //let spawnForeverAC = SKAction.repeatForever(spawnAC)
            //self.run(spawnForeverAC, withKey: "spawningAmmo")
            let spawnBullet = SKAction.run(fireEnemyBulletAsteroid)
            let waitToSpawnBullet = SKAction.wait(forDuration: 5.0)
            let spawnSequenceBullet = SKAction.sequence([waitToSpawnBullet, spawnBullet])
            let spawnForeverBullet = SKAction.repeatForever(spawnSequenceBullet)
            self.run(spawnForeverBullet, withKey: "spawingBullets")
            //let spawnBulletST = SKAction.run(fireEnemyBulletAsteroidST)
            //let waitToSpawnBulletST = SKAction.wait(forDuration: 4.5)
            //let spawnSequenceBulletST = SKAction.sequence([waitToSpawnBulletST, spawnBulletST])
            //let spawnForeverBulletST = SKAction.repeatForever(spawnSequenceBulletST)
            //self.run(spawnForeverBulletST, withKey: "spawingBulletsST")
            //let spawnBulletFNCY = SKAction.run(fireEnemyBulletAsteroidFNCY)
            //let waitToSpawnBulletFNCY = SKAction.wait(forDuration: 4.0)
            //let spawnSequenceBulletFNCY = SKAction.sequence([waitToSpawnBulletFNCY, spawnBulletFNCY])
            //let spawnForeverBulletFNCY = SKAction.repeatForever(spawnSequenceBulletFNCY)
            //self.run(spawnForeverBulletFNCY, withKey: "spawingBulletsFNCY")
        case 4:
            let spawn = SKAction.run(spawnAsteroid)
            let waitToSpawn = SKAction.wait(forDuration: 0.5)
            let spawnSequence = SKAction.sequence([waitToSpawn, spawn])
            let spawnForever = SKAction.repeatForever(spawnSequence)
            self.run(spawnForever, withKey: "spawningAsteroids")
            //let spawnST = SKAction.run(spawnAsteroidStrong)
            //let waitToSpawnST = SKAction.wait(forDuration: 1.35)
            //let spawnSequenceST = SKAction.sequence([waitToSpawnST, spawnST])
            //let spawnForeverST = SKAction.repeatForever(spawnSequenceST)
            //self.run(spawnForeverST, withKey: "spawningAsteroidsST")
            //let spawnFNCY = SKAction.run(spawnAsteroidFancy)
            //let waitToSpawnFNCY = SKAction.wait(forDuration: 1.5)
            //let spawnSequenceFNCY = SKAction.sequence([waitToSpawnFNCY, spawnFNCY])
            //let spawnForeverFNCY = SKAction.repeatForever(spawnSequenceFNCY)
            //self.run(spawnForeverFNCY, withKey: "spawningAsteroidsFNCY")
            //let spawnHD = SKAction.run(spawnAsteroidHard)
            //let waitToSpawnHD = SKAction.wait(forDuration: 1.75)
            //let spawnSequenceHD = SKAction.sequence([waitToSpawnHD, spawnHD])
            //let spawnForeverHD = SKAction.repeatForever(spawnSequenceHD)
            //self.run(spawnForeverHD, withKey: "spawningAsteroidsHD")
            //let spawnAC = SKAction.run(spawnAmmoCrate)
            //let spawnForeverAC = SKAction.repeatForever(spawnAC)
            //self.run(spawnForeverAC, withKey: "spawningAmmo")
            let spawnBullet = SKAction.run(fireEnemyBulletAsteroid)
            let waitToSpawnBullet = SKAction.wait(forDuration: 5.0)
            let spawnSequenceBullet = SKAction.sequence([waitToSpawnBullet, spawnBullet])
            let spawnForeverBullet = SKAction.repeatForever(spawnSequenceBullet)
            self.run(spawnForeverBullet, withKey: "spawingBullets")
            //let spawnBulletST = SKAction.run(fireEnemyBulletAsteroidST)
            //let waitToSpawnBulletST = SKAction.wait(forDuration: 4.5)
            //let spawnSequenceBulletST = SKAction.sequence([waitToSpawnBulletST, spawnBulletST])
            //let spawnForeverBulletST = SKAction.repeatForever(spawnSequenceBulletST)
            //self.run(spawnForeverBulletST, withKey: "spawingBulletsST")
            //let spawnBulletFNCY = SKAction.run(fireEnemyBulletAsteroidFNCY)
            //let waitToSpawnBulletFNCY = SKAction.wait(forDuration: 4.0)
            //let spawnSequenceBulletFNCY = SKAction.sequence([waitToSpawnBulletFNCY, spawnBulletFNCY])
            //let spawnForeverBulletFNCY = SKAction.repeatForever(spawnSequenceBulletFNCY)
            //self.run(spawnForeverBulletFNCY, withKey: "spawingBulletsFNCY")
            //let spawnBulletHD = SKAction.run(fireEnemyBulletAsteroidHD)
            //let waitToSpawnBulletHD = SKAction.wait(forDuration: 3.0)
            //let spawnSequenceBulletHD = SKAction.sequence([waitToSpawnBulletHD, spawnBulletHD])
            //let spawnForeverBulletHD = SKAction.repeatForever(spawnSequenceBulletHD)
            //self.run(spawnForeverBulletHD, withKey: "spawingBulletsHD")
        default:
            let spawn = SKAction.run(spawnAsteroid)
            let waitToSpawn = SKAction.wait(forDuration: 0.5)
            let spawnSequence = SKAction.sequence([waitToSpawn, spawn])
            let spawnForever = SKAction.repeatForever(spawnSequence)
            self.run(spawnForever, withKey: "spawningAsteroids")
            //let spawnST = SKAction.run(spawnAsteroidStrong)
            //let waitToSpawnST = SKAction.wait(forDuration: 1.35)
            //let spawnSequenceST = SKAction.sequence([waitToSpawnST, spawnST])
            //let spawnForeverST = SKAction.repeatForever(spawnSequenceST)
            //self.run(spawnForeverST, withKey: "spawningAsteroidsST")
            //let spawnFNCY = SKAction.run(spawnAsteroidFancy)
            //let waitToSpawnFNCY = SKAction.wait(forDuration: 1.5)
            //let spawnSequenceFNCY = SKAction.sequence([waitToSpawnFNCY, spawnFNCY])
            //let spawnForeverFNCY = SKAction.repeatForever(spawnSequenceFNCY)
            //self.run(spawnForeverFNCY, withKey: "spawningAsteroidsFNCY")
            //let spawnHD = SKAction.run(spawnAsteroidHard)
            //let waitToSpawnHD = SKAction.wait(forDuration: 1.75)
            //let spawnSequenceHD = SKAction.sequence([waitToSpawnHD, spawnHD])
            //let spawnForeverHD = SKAction.repeatForever(spawnSequenceHD)
            //self.run(spawnForeverHD, withKey: "spawningAsteroidsHD")
            //let spawnAC = SKAction.run(spawnAmmoCrate)
            //let spawnForeverAC = SKAction.repeatForever(spawnAC)
            //self.run(spawnForeverAC, withKey: "spawningAmmo")
            let spawnBullet = SKAction.run(fireEnemyBulletAsteroid)
            let waitToSpawnBullet = SKAction.wait(forDuration: 5.0)
            let spawnSequenceBullet = SKAction.sequence([waitToSpawnBullet, spawnBullet])
            let spawnForeverBullet = SKAction.repeatForever(spawnSequenceBullet)
            self.run(spawnForeverBullet, withKey: "spawingBullets")
            //let spawnBulletST = SKAction.run(fireEnemyBulletAsteroidST)
            //let waitToSpawnBulletST = SKAction.wait(forDuration: 4.5)
            //let spawnSequenceBulletST = SKAction.sequence([waitToSpawnBulletST, spawnBulletST])
            //let spawnForeverBulletST = SKAction.repeatForever(spawnSequenceBulletST)
            //self.run(spawnForeverBulletST, withKey: "spawingBulletsST")
           // let spawnBulletFNCY = SKAction.run(fireEnemyBulletAsteroidFNCY)
          //  let waitToSpawnBulletFNCY = SKAction.wait(forDuration: 4.0)
           // let spawnSequenceBulletFNCY = SKAction.sequence([waitToSpawnBulletFNCY, spawnBulletFNCY])
            //let spawnForeverBulletFNCY = SKAction.repeatForever(spawnSequenceBulletFNCY)
            //self.run(spawnForeverBulletFNCY, withKey: "spawingBulletsFNCY")
           // let spawnBulletHD = SKAction.run(fireEnemyBulletAsteroidHD)
            //let waitToSpawnBulletHD = SKAction.wait(forDuration: 3.0)
            //let spawnSequenceBulletHD = SKAction.sequence([waitToSpawnBulletHD, spawnBulletHD])
            //let spawnForeverBulletHD = SKAction.repeatForever(spawnSequenceBulletHD)
            //self.run(spawnForeverBulletHD, withKey: "spawingBulletsHD")
        }
    }
    
    func fireBullet(){
        
        let bullet = SKSpriteNode(imageNamed: "laser_bullet")
        bullet.name = "Bullet"
        bullet.setScale(0.5)
        bullet.position = player.position
        bullet.zPosition = 1
        bullet.physicsBody = SKPhysicsBody(texture: bullet.texture!, size: bullet.size)
        bullet.physicsBody!.affectedByGravity = false
        bullet.physicsBody!.categoryBitMask = PhysicsCategories.Bullet
        bullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        bullet.physicsBody!.contactTestBitMask = PhysicsCategories.Asteroid
        self.addChild(bullet)
        
        let moveBullet = SKAction.moveTo(y: self.size.height + bullet.size.height, duration: 1)
        let deleteBullet = SKAction.removeFromParent()
        let bulletSequence = SKAction.sequence([bulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            bullet.run(bulletSequence)
        }
        
    }
    
    func spawnExtraLife() {
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX , max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)
        
        let extraLife = SKSpriteNode(imageNamed: "ExtraLife")
        extraLife.name = "ExtraLife"
        extraLife.setScale(0.25)
        extraLife.position = startPoint
        extraLife.zPosition = 2
        extraLife.physicsBody = SKPhysicsBody(texture: extraLife.texture!, size: extraLife.size)
        extraLife.physicsBody!.affectedByGravity = false
        extraLife.physicsBody!.categoryBitMask = PhysicsCategories.ExtraLife
        extraLife.physicsBody!.collisionBitMask = PhysicsCategories.None
        extraLife.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(extraLife)
        
        let moveExtraLife = SKAction.move(to: endPoint, duration: 3.5)
        let deleteExtraLife = SKAction.removeFromParent()
        let ExtraLifeSequence = SKAction.sequence([moveExtraLife, deleteExtraLife])
        let ExtraLifeSequenceForever = SKAction.repeatForever(ExtraLifeSequence)
        
        if currentGameState == gameState.inGame{
            extraLife.run(ExtraLifeSequenceForever)
        }
        
    }
    
    func spawnAmmoCrate() {
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX , max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)
        
        let ammoCrate = SKSpriteNode(imageNamed: "Ammo")
        ammoCrate.name = "Ammo"
        ammoCrate.setScale(0.75)
        ammoCrate.position = startPoint
        ammoCrate.zPosition = 2
        ammoCrate.physicsBody = SKPhysicsBody(texture: ammoCrate.texture! , size: ammoCrate.size)
        ammoCrate.physicsBody!.affectedByGravity = false
        ammoCrate.physicsBody!.categoryBitMask = PhysicsCategories.AmmoCrate
        ammoCrate.physicsBody!.collisionBitMask = PhysicsCategories.None
        ammoCrate.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(ammoCrate)
        
        //let waitToSpawnAmmoCrate = SKAction.wait(forDuration: 5.0)
        let moveAmmoCrate = SKAction.move(to: endPoint, duration: 3.5)
        let deleteAmmoCrate = SKAction.removeFromParent()
        let ammoCrateSequence = SKAction.sequence([moveAmmoCrate, deleteAmmoCrate])
        let ammoCrateSequenceForever = SKAction.repeatForever(ammoCrateSequence)
        
        if currentGameState == gameState.inGame{
            ammoCrate.run(ammoCrateSequenceForever)
        }
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy, dx)
        ammoCrate.zRotation = amountToRotate
        
        
        
    }
    
    func spawnAsteroid() {
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX, max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)
        
        let asteroid = SKSpriteNode(imageNamed: "Asteroid")
        asteroid.name = "asteroid"
        asteroid.setScale(0.3)
        asteroid.position = startPoint
        asteroid.zPosition = 2
        asteroid.physicsBody = SKPhysicsBody(texture: asteroid.texture!,size: asteroid.size)
        asteroid.physicsBody!.affectedByGravity = false
        asteroid.physicsBody!.categoryBitMask = PhysicsCategories.Asteroid
        asteroid.physicsBody!.collisionBitMask = PhysicsCategories.None
        asteroid.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(asteroid)
        
        let moveAsteroid = SKAction.move(to: endPoint, duration: 3.5)
        let deleteAsteroid = SKAction.removeFromParent()
        let addScoreAction = SKAction.run(addScore)
        let asteroidSequence = SKAction.sequence([moveAsteroid, deleteAsteroid, addScoreAction])
        
        
        
        if currentGameState == gameState.inGame{
            asteroid.run(asteroidSequence)

        }
        
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy,dx)
        asteroid.zRotation = amountToRotate
        
        
        
    }
    
    func spawnAsteroidStrong() {
    
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX, max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)

        let asteroidST = SKSpriteNode(imageNamed: "StrongAsteroid")
        asteroidST.name = "STasteroid"
        asteroidST.setScale(0.2)
        asteroidST.position = startPoint
        asteroidST.zPosition = 2
        asteroidST.physicsBody = SKPhysicsBody(texture: asteroidST.texture!,size: asteroidST.size)
        asteroidST.physicsBody!.affectedByGravity = false
        asteroidST.physicsBody!.categoryBitMask = PhysicsCategories.StrongAsteroid
        asteroidST.physicsBody!.collisionBitMask = PhysicsCategories.None
        asteroidST.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(asteroidST)
        
        let moveAsteroidST = SKAction.move(to: endPoint, duration: 3.5)
        let deleteAsteroidST = SKAction.removeFromParent()
        let addScoreActionST = SKAction.run(addScoreST)
        let asteroidSequenceST = SKAction.sequence([moveAsteroidST, deleteAsteroidST, addScoreActionST])
        
        if currentGameState == gameState.inGame{
            asteroidST.run(asteroidSequenceST)
        }
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy,dx)
        asteroidST.zRotation = amountToRotate
        
        
        
    }
    
    func spawnAsteroidFancy() {
        
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX, max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)
        
        let asteroidFNCY = SKSpriteNode(imageNamed: "FancyAsteroid")
        asteroidFNCY.name = "FNCYasteroid"
        asteroidFNCY.setScale(0.20)
        asteroidFNCY.position = startPoint
        asteroidFNCY.zPosition = 2
        asteroidFNCY.physicsBody = SKPhysicsBody(texture: asteroidFNCY.texture!, size: asteroidFNCY.size)
        asteroidFNCY.physicsBody!.affectedByGravity = false
        asteroidFNCY.physicsBody!.categoryBitMask = PhysicsCategories.FancyAsteroid
        asteroidFNCY.physicsBody!.collisionBitMask = PhysicsCategories.None
        asteroidFNCY.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(asteroidFNCY)
        
        let moveAsteroidFNCY = SKAction.move(to: endPoint, duration: 3.5)
        let deleteAsteroidFNCY = SKAction.removeFromParent()
        let addScoreActionFNCY = SKAction.run(addScoreFNCY)
        let asteroidSequenceFNCY = SKAction.sequence([moveAsteroidFNCY, deleteAsteroidFNCY, addScoreActionFNCY])
        
        if currentGameState == gameState.inGame{
            asteroidFNCY.run(asteroidSequenceFNCY)
        }
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy,dx)
        asteroidFNCY.zRotation = amountToRotate
        
        
        
    }
    
    func spawnAsteroidHard() {
        
        let randomXStart = random(min: gameArea.minX , max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX, max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: -self.size.height * 0.2)
        
        let asteroidHD = SKSpriteNode(imageNamed: "AsteroidHard")
        asteroidHD.name = "HDasteroid"
        asteroidHD.setScale(0.40)
        asteroidHD.position = startPoint
        asteroidHD.zPosition = 2
        asteroidHD.physicsBody = SKPhysicsBody(texture: asteroidHD.texture!,size: asteroidHD.size)
        asteroidHD.physicsBody!.affectedByGravity = false
        asteroidHD.physicsBody!.categoryBitMask = PhysicsCategories.HardAsteroid
        asteroidHD.physicsBody!.collisionBitMask = PhysicsCategories.None
        asteroidHD.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(asteroidHD)
        
        let moveAsteroidHD = SKAction.move(to: endPoint, duration: 3.5)
        let deleteAsteroidHD = SKAction.removeFromParent()
        let addScoreActionHD = SKAction.run(addScoreHD)
        let asteroidSequenceHD = SKAction.sequence([moveAsteroidHD, deleteAsteroidHD, addScoreActionHD])
        
        if currentGameState == gameState.inGame{
            asteroidHD.run(asteroidSequenceHD)
        }
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy,dx)
        asteroidHD.zRotation = amountToRotate
        
        
        
    }
    
    func spawnBoss() {
        let randomXStart = random(min: gameArea.minX, max: gameArea.maxX)
        let randomXEnd = random(min: gameArea.minX, max: gameArea.maxX)
        let randomXUp = random(min: gameArea.minX, max: gameArea.maxX)
        let startPoint = CGPoint(x: randomXStart, y: self.size.height * 1.2)
        let endPoint = CGPoint(x: randomXEnd, y: self.size.height * 0.5)
        let upPoint = CGPoint(x: randomXUp, y: self.size.height)
        
        boss.setScale(0.8)
        boss.position = startPoint
        boss.zPosition = 2
        boss.physicsBody = SKPhysicsBody(texture:boss.texture!,size: boss.size)
        boss.physicsBody!.affectedByGravity = false
        boss.physicsBody!.categoryBitMask = PhysicsCategories.Boss
        boss.physicsBody!.collisionBitMask = PhysicsCategories.None
        boss.physicsBody!.contactTestBitMask = PhysicsCategories.Bullet | PhysicsCategories.Player
        
        self.addChild(boss)
        
        let moveBoss = SKAction.move(to: endPoint, duration: 4.0)
        let moveBossUp = SKAction.move(to: upPoint, duration: 4.0)
        let BossSequence = SKAction.sequence([moveBoss, moveBossUp])
        let BossSequenceRepeatForever = SKAction.repeatForever(BossSequence)
        
        if currentGameState == gameState.inGame{
            boss.run(BossSequenceRepeatForever)
        }
        
        let dx = endPoint.x - startPoint.x
        let dy = endPoint.y - startPoint.y
        let amountToRotate = atan2(dy,dx)
        boss.zRotation = amountToRotate
        
        
        
    }
    
    func fireEnemyBulletBoss(){
        
        Enemybullet.setScale(0.5)
        Enemybullet.position = boss.position
        Enemybullet.zPosition = 1
        Enemybullet.physicsBody = SKPhysicsBody(texture: Enemybullet.texture!,size: Enemybullet.size)
        Enemybullet.physicsBody!.affectedByGravity = false
        Enemybullet.physicsBody!.categoryBitMask = PhysicsCategories.EnemyBullet
        Enemybullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        Enemybullet.physicsBody!.contactTestBitMask = PhysicsCategories.Player | PhysicsCategories.Bullet
        self.addChild(Enemybullet)
        
        let moveBullet = SKAction.moveTo(y: -self.size.height - Enemybullet.size.height, duration: 2.0)
        let deleteBullet = SKAction.removeFromParent()
        let bulletSequence = SKAction.sequence([EnemybulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            Enemybullet.run(bulletSequence)
        }
        
        
    }
    
    func fireEnemyBulletAsteroid(){
        
        Enemybullet.setScale(0.5)
        Enemybullet.position = asteroid.position
        Enemybullet.zPosition = 1
        Enemybullet.physicsBody = SKPhysicsBody(texture: Enemybullet.texture!,size: Enemybullet.size)
        Enemybullet.physicsBody!.affectedByGravity = false
        Enemybullet.physicsBody!.categoryBitMask = PhysicsCategories.EnemyBullet
        Enemybullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        Enemybullet.physicsBody!.contactTestBitMask = PhysicsCategories.Player | PhysicsCategories.Bullet
        self.addChild(Enemybullet)
        
        let moveBullet = SKAction.moveTo(y: -self.size.height - Enemybullet.size.height, duration: 2.0)
        let deleteBullet = SKAction.removeFromParent()
        let bulletSequence = SKAction.sequence([EnemybulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            Enemybullet.run(bulletSequence)
        }
        
        
    }
    
    func fireEnemyBulletAsteroidST(){
        
        Enemybullet.setScale(0.5)
        Enemybullet.position = asteroidST.position
        Enemybullet.zPosition = 1
        Enemybullet.physicsBody = SKPhysicsBody(texture: Enemybullet.texture!,size: Enemybullet.size)
        Enemybullet.physicsBody!.affectedByGravity = false
        Enemybullet.physicsBody!.categoryBitMask = PhysicsCategories.EnemyBullet
        Enemybullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        Enemybullet.physicsBody!.contactTestBitMask = PhysicsCategories.Player | PhysicsCategories.Bullet
        self.addChild(Enemybullet)
        
        let moveBullet = SKAction.moveTo(y: -self.size.height - Enemybullet.size.height, duration: 2.0)
        let deleteBullet = SKAction.removeFromParent()
        let waitToSpawnBullet = SKAction.wait(forDuration: 1.0)
        let bulletSequence = SKAction.sequence([waitToSpawnBullet, EnemybulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            Enemybullet.run(bulletSequence)
        }
        
        
    }
    
    func fireEnemyBulletAsteroidFNCY(){
        
        Enemybullet.setScale(0.5)
        Enemybullet.position = asteroidFNCY.position
        Enemybullet.zPosition = 1
        Enemybullet.physicsBody = SKPhysicsBody(texture: Enemybullet.texture!,size: Enemybullet.size)
        Enemybullet.physicsBody!.affectedByGravity = false
        Enemybullet.physicsBody!.categoryBitMask = PhysicsCategories.EnemyBullet
        Enemybullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        Enemybullet.physicsBody!.contactTestBitMask = PhysicsCategories.Player | PhysicsCategories.Bullet
        self.addChild(Enemybullet)
        
        let moveBullet = SKAction.moveTo(y: -self.size.height - Enemybullet.size.height, duration: 2.0)
        let deleteBullet = SKAction.removeFromParent()
        let waitToSpawnBullet = SKAction.wait(forDuration: 1.0)
        let bulletSequence = SKAction.sequence([waitToSpawnBullet, EnemybulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            Enemybullet.run(bulletSequence)
        }
        
        
    }

    func fireEnemyBulletAsteroidHD(){
        
        Enemybullet.setScale(0.5)
        Enemybullet.position = asteroidHD.position
        Enemybullet.zPosition = 1
        Enemybullet.physicsBody = SKPhysicsBody(texture: Enemybullet.texture!,size: Enemybullet.size)
        Enemybullet.physicsBody!.affectedByGravity = false
        Enemybullet.physicsBody!.categoryBitMask = PhysicsCategories.EnemyBullet
        Enemybullet.physicsBody!.collisionBitMask = PhysicsCategories.None
        Enemybullet.physicsBody!.contactTestBitMask = PhysicsCategories.Player | PhysicsCategories.Bullet
        self.addChild(Enemybullet)
        
        let moveBullet = SKAction.moveTo(y: -self.size.height - Enemybullet.size.height, duration: 2.0)
        let deleteBullet = SKAction.removeFromParent()
        let waitToSpawnBullet = SKAction.wait(forDuration: 1.0)
        let bulletSequence = SKAction.sequence([waitToSpawnBullet, EnemybulletSound, moveBullet, deleteBullet])
        
        if currentGameState == gameState.inGame{
            Enemybullet.run(bulletSequence)
        }
        
        
    }

    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        if currentGameState == gameState.preGame {
            startGame()
        }
        for touch: AnyObject in touches{
            
            let pointOfTouch = touch.location(in: self)
            
            if FireButton.contains(pointOfTouch){
                if bulletlives > 0{
                fireBullet()
                loseABulletLife()
                }
            }
        }
    }
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch: AnyObject in touches{
            let pointofTouch = touch.location(in: self)
            let previousPointofTouch = touch.previousLocation(in: self)
            
            let amountofXDragged = pointofTouch.x - previousPointofTouch.x
            let amountofYDragged = pointofTouch.y - previousPointofTouch.y
            
            if currentGameState == gameState.inGame{
                player.position.x += amountofXDragged * 1.2
                player.position.y += amountofYDragged * 1.2
            }
           
            if player.position.x > gameArea.maxX - player.size.width/2 {
                player.position.x = gameArea.maxX - player.size.width/2
            }
            if player.position.x < gameArea.minX + player.size.width/2 {
                player.position.x = gameArea.minX + player.size.width/2
            }
            if player.position.y > gameArea.maxY - player.size.height/2 {
                player.position.y = gameArea.maxY - player.size.height/2
            }
            if player.position.y < gameArea.minY + player.size.height/2 {
                player.position.y = gameArea.minY + player.size.height/2
            }
            }
        

    }
    }



