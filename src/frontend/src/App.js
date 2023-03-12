import {useRef} from "react";
import Form from "./components/Form";
import Grid from "./components/Grid";

export default function App() {
  const grid = useRef(null);

  const submit = (username) => {
      console.log(username);
      console.log(grid.current.getToggled().sort((a, b) => a - b));
  }

  return (
    <div className="container my-5 py-3 px-3 shadow">
      <Form onSubmit={submit}/>
      <Grid size={process.env.REACT_APP_GRID_SIZE} ref={grid}/>
    </div>
  );
}