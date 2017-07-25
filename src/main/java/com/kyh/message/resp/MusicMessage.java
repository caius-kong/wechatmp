package com.kyh.message.resp;

/**
 * 音乐消息（多媒体消息）
 */
public class MusicMessage extends BaseMessage {
    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        Music = music;
    }
}