//
//  GameOverScene.swift
//  Void_Protector
//
//  Created by Sneh Patel on 12/29/17.
//  Copyright © 2017 AmDa LLC. All rights reserved.
//

import Foundation
import SpriteKit

class GameOverScene: SKScene{
   
let restartLabel = SKLabelNode(fontNamed: "The Bold Font")
    
    override func didMove(to view: SKView) {
        
        let background = SKSpriteNode(imageNamed: "background")
        background.size = self.size
        background.position = CGPoint(x: self.size.width/2, y: self.size.height/2)
        background.zPosition = 0
        self.addChild(background)
        
        let gameOverLabel = SKLabelNode(fontNamed: "The Bold Font")
        gameOverLabel.text = "Game Over"
        gameOverLabel.fontSize = 200
        gameOverLabel.fontColor = SKColor.lightGray
        gameOverLabel.position = CGPoint(x: self.size.width * 0.5, y: self.size.height * 0.75)
        gameOverLabel.zPosition = 1
        self.addChild(gameOverLabel)
        
        let ScoreLable = SKLabelNode(fontNamed: "The Bold Font")
        ScoreLable.text = "Score: \(gameScore)"
        ScoreLable.fontSize = 100
        ScoreLable.fontColor = SKColor.lightGray
        ScoreLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.60)
        ScoreLable.zPosition = 1
        self.addChild(ScoreLable)
        
        let AmmoLable = SKLabelNode(fontNamed: "The Bold Font")
        AmmoLable.text = "Ammo Left: \(bulletlives)"
        AmmoLable.fontSize = 100
        AmmoLable.fontColor = SKColor.lightGray
        AmmoLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.50)
        AmmoLable.zPosition = 1
        self.addChild(AmmoLable)
        
        let defaults = UserDefaults()
        var highScoreNumber = defaults.integer(forKey: "highScoreSaved")
        var highAmmoNumber = defaults.integer(forKey: "highAmmoSaved")
        
        if gameScore > highScoreNumber{
            highScoreNumber = gameScore
            defaults.set(highScoreNumber, forKey: "highScoreSaved")
        }
        
        if bulletlives > highAmmoNumber{
            highAmmoNumber = bulletlives
            defaults.set(highAmmoNumber, forKey: "highAmmoSaved")
        }
        
        let highScoreLable = SKLabelNode(fontNamed: "The Bold Font")
        highScoreLable.text = "High Score: \(highScoreNumber)"
        highScoreLable.fontSize = 110
        highScoreLable.fontColor = SKColor.lightGray
        highScoreLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.40)
        highScoreLable.zPosition = 1
        self.addChild(highScoreLable)
        
        let highAmmoLable = SKLabelNode(fontNamed: "The Bold Font")
        highAmmoLable.text = "Highest Ammo: \(highAmmoNumber)"
        highAmmoLable.fontSize = 110
        highAmmoLable.fontColor = SKColor.lightGray
        highAmmoLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.30)
        highAmmoLable.zPosition = 1
        self.addChild(highAmmoLable)
        
        restartLabel.text = "Restart"
        restartLabel.fontSize = 120
        restartLabel.fontColor = SKColor.lightGray
        restartLabel.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.15)
        restartLabel.zPosition = 1
        self.addChild(restartLabel)
    }
    
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        for touch: AnyObject in touches{
            
            let pointOfTouch = touch.location(in: self)
            
            if restartLabel.contains(pointOfTouch){
                
                let sceneToMoveTo = GameScene(size: self.size)
                sceneToMoveTo.scaleMode = self.scaleMode
                let myTransition = SKTransition.fade(withDuration: 0.5)
                self.view!.presentScene(sceneToMoveTo, transition: myTransition)
                
                
                setBulletLives()
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
