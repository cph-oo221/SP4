package main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public Sound()
    {
        soundURL[0] = getClass().getResource("/sound/Main Theme 2DGame.wav");
        soundURL[1] = getClass().getResource("/sound/Item Gained.wav");
        soundURL[2] = getClass().getResource("/sound/Quest completed.wav");
        soundURL[3] = getClass().getResource("/sound/Metal.wav");
        soundURL[4] = getClass().getResource("/sound/Dungeon1.wav");
        soundURL[5] = getClass().getResource("/sound/DeathMusic.wav");
        soundURL[6] = getClass().getResource("/sound/WinMusic.wav");
        soundURL[7] = getClass().getResource("/sound/pain.wav");
    }

    public void setFile(int i) {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);


        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
