//
//  GameViewController.swift
//  Void_Protector
//
//  Created by Sneh Patel on 12/27/17.
//  Copyright © 2017 AmDa LLC. All rights reserved.
//

import UIKit
import SpriteKit
import GameplayKit
import AVFoundation

class GameViewController: UIViewController {
    
    var backingAudio = AVAudioPlayer()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        let filePath = Bundle.main.path(forResource: "BackgroundMusic", ofType: "mp3")
        let audioNSURL = NSURL(fileURLWithPath: filePath!)
        
        do { backingAudio = try AVAudioPlayer(contentsOf: audioNSURL as URL)}
        catch { return print("Cannot Find The Audio")}
        
        backingAudio.numberOfLoops = -1
        backingAudio.volume = 1
        backingAudio.play()
        
        if let view = self.view as! SKView? {
            // Load the SKScene from 'GameScene.sks'
            let scene = BeginningScene(size: CGSize(width: 1536, height: 2048))
            // Set the scale mode to scale to fit the window
                scene.scaleMode = .aspectFill
                
                // Present the scene
                view.presentScene(scene)
            
            
            view.ignoresSiblingOrder = true
            
            view.showsFPS = true
            view.showsNodeCount = true
        }
    }

    override var shouldAutorotate: Bool {
        return true
    }

    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        if UIDevice.current.userInterfaceIdiom == .phone {
            return .allButUpsideDown
        } else {
            return .all
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Release any cached data, images, etc that aren't in use.
    }

    override var prefersStatusBarHidden: Bool {
        return true
    }
}
