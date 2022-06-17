import React from 'react'

import './timeCapsule.css'
import JoyStickPannel from '../componenets/JoyStickPannel/JoyStickPannel';
import LineChart from '../componenets/LineChart/LineChart';
import SingleSelectList from '../componenets/SingleSelectList/SingleSelectList';
import ScatterGraph from '../componenets/ScatterGraph/ScatterGraph';
import VideoBar from '../componenets/VideoBar/VideoBar';

import { getLineGraphChangesOverTime } from '../DB/db';

const TimeCapsule = () => {
  return (
    <div className='monitoring__container'>

    <main className='top__monitoring__panel'>
      <SingleSelectList listItems={['Flight 1','Flight 2','Flight 3','Flight 4']} />
      <div className='monitoring__graphs'>
        <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
        <LineChart className='changes__over__time__graph' graphData={getLineGraphChangesOverTime()} />
      </div>
      <JoyStickPannel />
    </main>
    
    <div className='scatterGraph__container'>
      <ScatterGraph />
    </div>

    <VideoBar/>
  </div>
  )
}

export default TimeCapsule;