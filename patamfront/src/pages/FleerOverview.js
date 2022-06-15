import './FleetOverview.css'
import React from 'react'

import { getMilesTraveledData, getAverageYearlyDistance, getFleetSizeYearly } from '../DB/db';

import PieGraph from '../componenets/PieChart/PieGraph';
import BarChart from '../componenets/BarChart/barChart';
import LineChart from '../componenets/LineChart/LineChart';


const FleerOverview = () => {

  return (
    <>
      <div>TODO render dummy map in here</div>

        <PieGraph />
        <LineChart graphData={getFleetSizeYearly()} />
        <BarChart graphData={getMilesTraveledData()} />
        <BarChart graphData={getAverageYearlyDistance()} />
      <button className='centered refresh__button'>Refresh Data</button>
    </>
  )
}

export default FleerOverview