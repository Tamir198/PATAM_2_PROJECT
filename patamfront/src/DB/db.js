export const getMilesTraveledData = () => { 
   //This is what will show on the graph
   const planesDistanceData = [
    { name: 'Plane 1', yAxis: 2 },
    { name: 'Plane 2', yAxis: 4 },
    { name: 'Plane 3', yAxis: 6 },
    { name: 'Plane 4', yAxis: 10 },
    { name: 'Plane 5', yAxis: 12 },
    { name: 'Plane 6', yAxis: 15 },
    { name: 'Plane 8', yAxis: 20 },
    { name: 'Plane 9', yAxis: 21 },
    { name: 'Plane 10', yAxis: 22 },
  ]
  
  const milesTraveledData = {
    labels: planesDistanceData.map((label) => label.name),
    datasets: [
      {
        label: 'Nautical miles  traveled fron begining of the month',
        data: planesDistanceData.map((planesData) => planesData.yAxis),
        backgroundColor: 'rgba( 255, 99, 132, 0.5)'
      }

    ],
  };

  return milesTraveledData;
}

export const getAverageYearlyDistance = () => { 
  //This is what will show on the graph
  const planesDistanceData = [
   { name: 'January', yAxis: 2 },
   { name: 'February', yAxis: 4 },
   { name: 'March', yAxis: 6 },
   { name: 'April', yAxis: 4 },
   { name: 'May', yAxis: 4 },
   { name: 'June', yAxis: 4 }
 ]
 
 const milesTraveledData = {
   labels: planesDistanceData.map((label) => label.name),
   datasets: [
     {
       label: `Average Fleet Distance for this month`,
       data: planesDistanceData.map((planesData) => planesData.yAxis),
       backgroundColor: 'rgba( 227, 134, 39, 0.5)'
     }

   ],
 };

 return milesTraveledData;
}