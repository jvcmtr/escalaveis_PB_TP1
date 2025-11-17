import React from 'react';
import { COLORS } from '../services/StyleService';

export default function InlineButton(props) {
    const [hover, setHover] = React.useState(false);

    return (
        <span
            onMouseEnter={setHover.bind(this, true)}
            onMouseLeave={setHover.bind(this, false)}
            {...props}
            style={getStyle(hover, props.style)}
        >
            {props.children}
        </span>
    );
}

function getStyle(hover, customStyle){
    return {
        cursor: "pointer",
        padding: '0.2em 0.5em',
        borderRadius: '0.5em',
        backgroundColor: hover? COLORS.softDim : "transparent", 
        color: hover?  COLORS.softHighlight: COLORS.highlight, 
        border: 'none',
        ...customStyle  
    }
}
