//
//  MainMenuScene.swift
//  Void_Protector
//
//  Created by Sneh Patel on 1/9/18.
//  Copyright © 2018 AmDa LLC. All rights reserved.
//

import Foundation
import SpriteKit

class MainMenuScene: SKScene{
    
    override func didMove(to view: SKView) {
        
        let background = SKSpriteNode(imageNamed: "background")
        background.size = self.size
        background.position = CGPoint(x: self.size.width/2, y: self.size.height/2)
        background.zPosition = 0
        self.addChild(background)
        
        let gameByLable = SKLabelNode(fontNamed: "The Bold Font")
        gameByLable.text = "AmDa LLC's"
        gameByLable.fontSize = 35
        gameByLable.fontColor = SKColor.lightGray
        gameByLable.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.78)
        gameByLable.zPosition = 1
        self.addChild(gameByLable)
        
        let gameNameLable1 = SKLabelNode(fontNamed: "The Bold Font")
        gameNameLable1.text = "Void"
        gameNameLable1.fontSize = 190
        gameNameLable1.fontColor = SKColor.lightGray
        gameNameLable1.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.70)
        gameNameLable1.zPosition = 1
        self.addChild(gameNameLable1)
        
        let gameNameLable2 = SKLabelNode(fontNamed: "The Bold Font")
        gameNameLable2.text = "Protectors"
        gameNameLable2.fontSize = 190
        gameNameLable2.fontColor = SKColor.lightGray
        gameNameLable2.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.625)
        gameNameLable2.zPosition = 1
        self.addChild(gameNameLable2)
        
        let startGame = SKLabelNode(fontNamed: "The Bold Font")
        startGame.text = "Start Game"
        startGame.fontSize = 150
        startGame.fontColor = SKColor.lightGray
        startGame.position = CGPoint(x: self.size.width / 2, y: self.size.height * 0.4)
        startGame.zPosition = 1
        startGame.name = "StartGameButton"
        self.addChild(startGame)
        
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        for touch: AnyObject in touches{
            let pointOfTouch = touch.location(in: self)
            let nodeITapped = atPoint(pointOfTouch)
    
            if nodeITapped.name == "StartGameButton" {
                
                let sceneToMoveTo = GameScene(size: self.size)
                sceneToMoveTo.scaleMode = self.scaleMode
                let myTransition = SKTransition.fade(withDuration: 0.5)
                self.view!.presentScene(sceneToMoveTo, transition: myTransition)
                
            }
        }
    }
    
    
    
    
    
    
    
}
