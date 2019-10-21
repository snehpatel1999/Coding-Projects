//
//  BeginningScene.swift
//  Void_Protector
//
//  Created by Sneh Patel on 3/17/18.
//  Copyright © 2018 AmDa LLC. All rights reserved.
//

import Foundation
import SpriteKit




class BeginningScene: SKScene{
    
    let AmDaVideo = SKVideoNode(fileNamed: "AmDaLogo.mp4")
    
    override func didMove(to view: SKView) {
        
        //let background = SKSpriteNode(imageNamed: "BlackBackground")
        //background.size = self.size
        //background.position = CGPoint(x: self.size.width/2, y: self.size.height/2)
        //background.zPosition = 0
        //self.addChild(background)
        
        AmDaVideo.size = CGSize(width: self.size.width/2, height: self.size.height/2)
        AmDaVideo.position = CGPoint(x: self.size.width/2, y: self.size.height/2)
        AmDaVideo.zPosition = 0
        addChild(AmDaVideo)
        AmDaVideo.play()
        
        let runfunc = SKAction.run(changeScene)
        let waitfor = SKAction.wait(forDuration: 4)
        let funcSequence = SKAction.sequence([waitfor, runfunc])
        self.run(funcSequence)
    }

    func changeScene() {
        let sceneToMoveTo = MainMenuScene(size: self.size)
        sceneToMoveTo.scaleMode = self.scaleMode
        let myTransition = SKTransition.fade(withDuration: 1)
        self.view!.presentScene(sceneToMoveTo, transition: myTransition)
    
        
    
    }

    
    
    
    
    
}
