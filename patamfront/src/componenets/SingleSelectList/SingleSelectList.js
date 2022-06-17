import React from 'react'

import './SingleSelectList.css'

const SingleSelectList = ({ listItems }) => {
  return (
    <>
      <ul className='select__list' >
        {listItems.map((item,index) => <li key={index}>{item}</li>)}
      </ul>
    </>
  )
}

export default SingleSelectList