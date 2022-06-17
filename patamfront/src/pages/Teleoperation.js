import React from 'react'
import './teleoperation.css'

import JoyStickPannel from '../componenets/JoyStickPannel/JoyStickPannel'

const Teleoperation = () => {
  return (
    <div className='teleoperation__container'>
      <textarea aria-label='sad' cols="30" rows="10"></textarea>
      <JoyStickPannel />
    </div>
  )
}

export default Teleoperation