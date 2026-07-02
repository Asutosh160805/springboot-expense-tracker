function  Card(props){

    return (

          <div>

              <h1>{props.title}</h1>

              <p>{props.value}</p>

          </div>
    );
}

export default Card;