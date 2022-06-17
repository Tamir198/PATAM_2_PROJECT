import React from 'react'
import './videoBar.css'

const VideoBar = () => {
  return (
    <div className="video__player">

      <div class="player__controls">
        <div class="progress">
          <div class="progress__filled"></div>
        </div>

        <button class="player__button toggle" title="Toggle Play">►</button>

        <label htmlFor="volume">volume</label>
        <input id="volume" type="range" name="volume" className="player__slider" min="0" max="1" step="0.05" />

        <label htmlFor="playbackRate">playback speed</label>
        <input id="playbackRate" type="range" name="playbackRate" class="player__slider" min="0.5" max="2" step="0.1" />

        <button data-skip="-10" class="player__button">« 10s</button>
        <button data-skip="25" class="player__button">25s »</button>
      </div>
    </div>
  )
}

export default VideoBar