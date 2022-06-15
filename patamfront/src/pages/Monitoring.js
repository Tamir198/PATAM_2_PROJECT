import React from 'react'

import './Monitoring.css'
import JoyStickPannel from '../componenets/JoyStickPannel/JoyStickPannel'
import SingleSelectList from '../componenets/SingleSelectList/SingleSelectList'
import LineChart from '../componenets/LineChart/LineChart'
import ScatterGraph from '../componenets/ScatterGraph/ScatterGraph'

import { getLineGraphChangesOverTime } from '../DB/db'

const Monitoring = () => {
  return (
    <div className='monitoring__container'>

      <main className='top__monitoring__panel'>
        <SingleSelectList listItems={['attribute-roll-deg', 'attribute-pitch-deg', 'Skid-bal', 'vertical-speed', 'horizontal-speed']} />
        <div className='monitoring__graphs'>
          <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
          <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
        </div>
        <JoyStickPannel />
      </main>

      <div className='scatterGraph__container'>
        <ScatterGraph />
      </div>
    </div>
  )
}

export default Monitoring