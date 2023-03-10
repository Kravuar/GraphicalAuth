import {useRef} from "react";

export default function Form({onSubmit}) {
    const username = useRef(null);

    return(
        <div className="form row">
            <label htmlFor="uname">Enter your username: </label>
            <input type="text" placeholder="Username..." name="username" id="uname" ref={username}/>
            <button className="btn btn-outline-success" onClick={() => onSubmit(username)}>Login</button>
        </div>
    );
}