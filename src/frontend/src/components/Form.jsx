import {useRef} from "react";

export default function Form({onSubmit}) {
    const username = useRef(null);

    return(
        <div className="container centered">
            <div className="row my-2">
                <input className="form-control my-1" type="text" placeholder="Username..." name="username" ref={username}/>
                <button className="btn btn-outline-success my-1" onClick={() => onSubmit(username.current.value)}>Login</button>
            </div>
        </div>
    );
}