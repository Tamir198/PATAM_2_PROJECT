import React from 'react'
import { Joystick } from 'react-joystick-component'

import './JoyStickPannel.css'

const JoyStickPannel = () => {
  return (
    <div className='upper__slider'>
      <label className='slider__vertical' htmlFor="horizontal__slider">Throttle</label>
      <input type="range" min="1" max="100" class="slider" id='horizontal__slider' />

      <Joystick
        size={100}
        sticky={true}
        baseColor="grey"
        stickColor="wheat"
        move={() => (console.log('Moving'))}
        stop={() => (console.log('No More moving'))}
      />

      <label htmlFor="vertical__slider">Stabilizer</label>
      <input type="range" min="1" max="100" class="slider" id='vertical__slider' />
    </div>

  )
}

export default JoyStickPannel