function Page(){
    let title = "자료실";

    let ar1 = ['자바 바이블 예제', 'Continuous', '스프링 용어집', 'AI활용'];


    return(
        <div>
            <h2 className="title">{title}</h2>
            <hr/>
            { /* ar1이라는 배열의 요소들을 data라는 변수에 전달하면서 반복하는 문장! */
                ar1.map(function(data, i){ /* i는 index를 의미함 */
                    return(
                        <p className="box" key={i}>
                            <header>
                                <h4>{data}</h4>
                                <p>2025-09-0{i+1}</p>
                                <div>
                                    <img src={`/images/book${i+1}.png`} className="book"/>
                                </div>
                            </header>
                        </p>
                    );
                })
            }
        </div>
    );
}

export default Page;