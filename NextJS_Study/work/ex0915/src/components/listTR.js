
export default function bbsTR(props){ 
    return(
        <p className="bord">
            <header>
                <h4>{props.subject}</h4>
                <p>{props.date}</p>
            </header>
        </p>
    );
};