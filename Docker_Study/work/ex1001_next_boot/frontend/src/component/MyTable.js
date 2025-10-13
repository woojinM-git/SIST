import { Card, CardContent, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";

export default function MyTable({ar}){

    return(
        /* mui 설치 후 card 안에 TableContainer를 활용하여
                ar의 요소들을 표현하기 */
        <Card style={{width: '700px', margin: '20px auto', marginTop: '50px'}}>
            <CardContent>
                <TableContainer component={Paper}>
                    <Table aria-label="simple table">
                        <TableHead>
                        <TableRow>
                            <TableCell align="center">번호</TableCell>
                            <TableCell align="center">이름</TableCell>
                            <TableCell align="center">직종</TableCell>
                            <TableCell align="center">입사일</TableCell>
                            <TableCell align="center">부서코드</TableCell>
                        </TableRow>
                        </TableHead>
                        <TableBody>
                        {ar.map((row, i) => (
                            <TableRow
                            key={i}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                            <TableCell component="th" scope="row">
                                {row.empno}
                            </TableCell>
                            <TableCell align="right">{row.ename}</TableCell>
                            <TableCell align="right">{row.job}</TableCell>
                            <TableCell align="right">{row.hiredate}</TableCell>
                            <TableCell align="right">{row.deptno}</TableCell>
                            </TableRow>
                        ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            </CardContent>
        </Card>
    );
}