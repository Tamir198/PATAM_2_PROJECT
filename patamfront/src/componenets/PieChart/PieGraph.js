import './pieGraph.css'
import React from 'react'
import { PieChart } from 'react-minimal-pie-chart';

const PieGraph = ({activityOfPlanes}) => {
  const defaultLabelStyle = {
    fontSize: '5px',
    fontFamily: 'sans-serif',
  };

  const data = [
    { title: 'Active Planes', value: 7, color: '#E38627' },
    { title: 'Non Active Planes', value: 3, color: '#C13C37' },
  ];

  return (
    <>
      <h1 className='title'>Active VS Inactive PLanes</h1>
      <PieChart className='pie__chart'
        data={data}
        label={({ dataEntry }) => `${dataEntry.value} ${dataEntry.title}`}
        radius={50} 
        labelStyle={{
          ...defaultLabelStyle,
        }}
        labelPosition={60}
      />
    </>
  )
}

export default PieGraph