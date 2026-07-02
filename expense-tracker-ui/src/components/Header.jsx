function Header(props){

    return(

        <div>

            <h1>{props.title}</h1>

            <p>Welcome {props.username}</p>

        </div>

    );

}

export default Header;