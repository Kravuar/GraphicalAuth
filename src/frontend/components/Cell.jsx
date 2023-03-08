import {useState} from "react";

export default function Cell({onToggle, isToggled}) {
    const [state, setState] = useState(isToggled);

    const toggle = () => {
        onToggle();
        setState(!state)
    }

    return <div className={`cell ${isToggled ? 'toggled' : ''}`}
                onClick={toggle}/>
}