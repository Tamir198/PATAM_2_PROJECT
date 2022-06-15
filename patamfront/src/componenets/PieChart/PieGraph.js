import './pieGraph.css'
import React from 'react'
import { PieChart } from 'react-minimal-pie-chart';

const PieGraph = ({activityOfPlanes}) => {
  const defaultLabelStyle = {
    fontSize: '5px',
    fontFamily: 'sans-serif',
  };

  const data = [
    { title: 'Active Planes', value: 10, color: '#E38627' },
    { title: 'Non Active Planes', value: 15, color: '#C13C37' },
  ];

  return (
    <>
      <PieChart className='pie__chart'
        data={data}
        label={({ dataEntry }) => `${dataEntry.value} ${dataEntry.title}`}
        radius={20} 
        labelStyle={{
          ...defaultLabelStyle,
        }}
        labelPosition={60}
      />
    </>
  )
}

export default PieGraph