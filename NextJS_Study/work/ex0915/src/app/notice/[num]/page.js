
export default function page(props){ 
    return(
        <div>
            <h1 className="title">상세보기</h1>
            <hr/>
            <div>
                <h2>{props.params.num}</h2>
            </div>
        </div>
    );
};