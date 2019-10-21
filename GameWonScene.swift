//
//  GameWonScene.swift
//  Void_Protector
//
//  Created by Sneh Patel on 12/29/17.
//  Copyright © 2017 AmDa LLC. All rights reserved.
//

import Foundation
import SpriteKit

class GameWonScene: SKScene{
    
    let restartLabel = SKLabelNode(fontNamed: "The Bold Font")
    
    override func didMove(to view: SKView) {
        
        let background = SKSpriteNode(imageNamed: "background")
        background.size = self.size
        background.position = CGPoint(x: self.size.width/2, y: self.size.height/2)
        background.zPosition = 0
        self.addChild(background)
        
        let youWonLabel = SKLabelNode(fontNamed: "The Bold Font")
        youWonLabel.text = "YOU WON!!!"
        youWonLabel.fontSize = 200
        youWonLabel.fontColor = SKColor.white
        youWonLabel.position = CGPoint(x: self.size.width * 0.5, y: self.size.height * 0.7)
        youWonLabel.zPosition = 1
        self.addChild(youWonLabel)
        
        let ScoreLable = SKLabelNode(fontNamed: "The Bold Font")
        ScoreLable.text = "Score: \(gameScore)"
        ScoreLable.fontSize = 125
        ScoreLable.fontColor = SKColor.white
        ScoreLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.55)
        ScoreLable.zPosition = 1
        self.addChild(ScoreLable)
        
        let defaults = UserDefaults()
        var highScoreNumber = defaults.integer(forKey: "highScoreSaved")
        
        if gameScore > highScoreNumber{
            highScoreNumber = gameScore
            defaults.set(highScoreNumber, forKey: "highScoreSaved")
        }
        
        let highScoreLable = SKLabelNode(fontNamed: "The Bold Font")
        highScoreLable.text = "High Score: \(highScoreNumber)"
        highScoreLable.fontSize = 125
        highScoreLable.fontColor = SKColor.white
        highScoreLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.45)
        highScoreLable.zPosition = 1
        self.addChild(highScoreLable)
        
        restartLabel.text = "Restart"
        restartLabel.fontSize = 90
        restartLabel.fontColor = SKColor.white
        restartLabel.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.3)
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
                
            }
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
