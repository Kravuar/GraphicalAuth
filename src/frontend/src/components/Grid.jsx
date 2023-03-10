import Cell from "./Cell";
import {forwardRef, useImperativeHandle} from "react";

function Grid({size, ref}) {
    const toggled = []

    useImperativeHandle(ref, () => ({
        getToggled: () => toggled
    }));

    const handleToggle = (gridIdx) => {
        if (toggled.includes(gridIdx))
            toggled.splice(toggled.indexOf(gridIdx), 1);
        else
            toggled.push(gridIdx);
    }

    const grid = [];
    for(let i = 0; i < size; ++i)
        for(let j = 0; j < size; ++j)
            grid.push(<Cell onToggle={() => handleToggle(i * size + j)}/>)
    
    return <div className="grid">{grid}</div>;
}

export default forwardRef(Grid);