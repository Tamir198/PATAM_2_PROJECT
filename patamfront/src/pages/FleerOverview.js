import './FleetOverview.css'
import React from 'react'

import { getMilesTraveledData, getAverageYearlyDistance } from '../DB/db';

import PieGraph from '../componenets/Graphs/PieGraph';
import { BarChart } from '../componenets/BarChart/barChart';



const FleerOverview = () => {

  return (
    <>
      <div>TODO render dummy map in here</div>
      <PieGraph />
      <BarChart graphData={getMilesTraveledData()} />
      <BarChart graphData={getAverageYearlyDistance()} />
      <button>Refresh Data</button>
    </>
  )
}

export default FleerOverview