export default function Lean() {
    let ar1 = [{title:'자바바이블', date:'2020-09-14', content:'이렇게 하는건가? 맞겠지?'},
                {title:'스프링 용어집', date:'2023-01-14', content:'이렇게 하는건가? 맞겠지?'},
                {title:'AI활용', date:'2025-09-14', content:'이렇게 하는건가? 맞겠지?'}]

    return(
      <div>
        {
            ar1.map(function(data, i){
                return(
                    <div className="board" key={i}>
                            <h4>{data.title}</h4>
                            <h4>{data.date}</h4>
                            <h4>{data.content}</h4>
                    </div>
                );
            })
        }
      </div>
    );
};