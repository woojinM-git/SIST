"use client"

import { useState } from "react";



function Page(){
    let title = "자료실";

    let ar1 = ['자바 바이블 예제', 'Continuous', '스프링 용어집', 'AI활용'];
    let [cnt, setCnt] = useState([0, 0, 0]);

    return(
        <div>
            <h2 className="title">{title}</h2>
            <hr/>
            { /* ar1이라는 배열의 요소들을 data라는 변수에 전달하면서 반복하는 문장! */
                ar1.map(function(data, i){ /* i는 index를 의미함 */
                    return(
                        <div className="box" key={i}>
                            <header>
                                <h4>{data}</h4>
                                <p>2025-09-0{i+1}</p>
                                <div>
                                    <img src={`/images/book${i+1}.png`} className="book"/>
                                </div>
                                <div className="fr">
                                    <span>{cnt[i]}</span>&nbsp;
                                    <button onClick={function(){
                                        // 버튼을 클릭할 때마다 수행하는 곳
                                        // useState의 값을 증가하자!

                                        // 일단 useState의 값을 복사해온다
                                        let cnt2 = [...cnt]; // cnt의 내용복사
                                        // cnt2는 cnt가 기억하고있는 숫자 3개짜리 배열이다. 즉[0, 0, 0]
                                        ++cnt2[i];
                                        setCnt(cnt2);
                                    }}>+</button>
                                </div>
                            </header>
                        </div>
                    );
                })
            }
        </div>
    );
}

export default Page;