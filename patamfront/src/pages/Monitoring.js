import React from 'react'

import './Monitoring.css'
import JoyStickPannel from '../componenets/JoyStickPannel/JoyStickPannel'
import SingleSelectList from '../componenets/SingleSelectList/SingleSelectList'
import LineChart from '../componenets/LineChart/LineChart'

import { getLineGraphChangesOverTime } from '../DB/db'

const Monitoring = () => {
  return (
    <>
      <main className='monitoring__container'>
        <SingleSelectList listItems={['attribute-roll-deg', 'attribute-pitch-deg', 'Skid-bal', 'vertical-speed', 'horizontal-speed']} />
        <div className='monitoring__graphs'>
          <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
          <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
        </div>
        <JoyStickPannel />
      </main>

    </>

  )
}

export default Monitoring