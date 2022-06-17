import React from 'react'
import BingMapsReact from "bingmaps-react";

const Map = () => {
  const pushPin = {
    center: {
      latitude: 27.987850,
      longitude: 86.925026,
    },
    options: {
      title: "Mt. Everest",
    },
  }

  const pushPins = [pushPin];
  return (
    <BingMapsReact
      pushPins={pushPins}
      bingMapsKey="AptPM365A_mkKTHasBqBQMQFQkHuH4AKll-fX2Ya_DZJGCFXrrySxHVzH6LbEPTD"
      height="30em"
      mapOptions={{
        navigationBarMode: "square",
      }}
      width="30em"
      viewOptions={{
        center: { latitude: 42.360081, longitude: -71.058884 },
        mapTypeId: "road",
      }}
    />
  );
}

export default Map