import React  from 'react';
const htmlEntities = {
  'hearts' : '9829',
  'diamonds' : '9830',
  'clubs' : '9827',
  'spades' : '9824'
}

const Card = (props) => {

  const codeString = String.fromCharCode(htmlEntities[props.suit])
  return (
    <div className={`card ${props.suit} `}>
      <div className="top rank">{props.rank}</div>
      <div className="suit">{codeString}</div>

      <div className="bottom rank">{props.rank}</div>
    </div>
  )
}
export default Card;
