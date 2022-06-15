import React from 'react';
import {
  Chart as ChartJS,
  LinearScale,
  PointElement,
  LineElement,
  Tooltip,
  Legend,
} from 'chart.js';
import { Scatter } from 'react-chartjs-2';

ChartJS.register(LinearScale, PointElement, LineElement, Tooltip, Legend);

export const options = {
  scales: {
    y: {
      beginAtZero: true,
    },
  },
};

const max = 100;
const min = 0;
export const data = {
  datasets: [
    {
      label: 'Anomaly detection',
       data: Array.from({ length: 100 }, () => ({
        x: Math.random() * (max - min) + min,
        y: Math.random() * (max - min) + min,
      })),
      backgroundColor: 'rgba(255, 99, 132, 1)',
    },
  ],
};

const ScatterGraph = () => {
  return <Scatter options={options} data={data} />;
}

export default ScatterGraph;
