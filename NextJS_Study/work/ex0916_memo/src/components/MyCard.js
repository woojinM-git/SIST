import Link from 'next/link';
import {Card, CardContent} from '@mui/material';

export default function MyCard({list}) {  
    return(
        <Card style={{width: '500px', margin: '20px auto'}}>
            <CardContent>
            {list.map( (item, i) => {
                return(
                    <Link key={i} href={`/view/${item.idx}`}>
                        <div className="list-item">
                            <h4 className="item-list-h4">{item.content}</h4>
                            <p className="item-list-p">{item.writer}/{item.reg_date}</p>
                        </div>
                    </Link>
                );
            })}
            </CardContent>
        </Card>
    );
};