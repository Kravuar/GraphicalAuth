import {useRef} from "react";
import Form from "../components/Form";
import Grid from "../components/Grid";

export default function App() {
  const grid = useRef(null);

  const submit = (username) => {
      console.log(username);
      console.log(grid.current.getToggled());
  }

  return (
    <div className="container">
      <Form onSubmit={submit}/>
      <Grid size={process.env.REACT_APP_GRID_SIZE} ref={grid}/>
    </div>
  );
}