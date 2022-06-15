import React from 'react';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';
import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);
const options = {
  responsive: true,
  plugins: {
    legend: {
      position: 'top',
    },
    title: {
      display: true,
    },
    scales: {
      yAxes: [{
          ticks: {
              fontColor: "green",
              fontSize: 18,
              stepSize: 1,
              beginAtZero: true
          }
      }],
      xAxes: [{
          ticks: {
              fontColor: "purple",
              fontSize: 14,
              stepSize: 1,
              beginAtZero: true
          }
      }]
  }

  },
};

function LineChart({ graphData }) {
  return <Line options={options} data={graphData} />;
}

export default LineChart;
