import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";

export default function EmpTable({ar}) {
    return(
        <div>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell align="center">번호</TableCell>
                            <TableCell align="center">이름</TableCell>
                            <TableCell align="center">사번</TableCell>
                            <TableCell align="center">직종</TableCell>
                            <TableCell align="center">부서코드</TableCell>
                            <TableCell align="center">인원수</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {ar.map((row, i) => (
                            <TableRow
                            key={i}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                            >
                            <TableCell component="th" scope="row">
                                {i+1}
                            </TableCell>
                                <TableCell align="center">{row.ename}</TableCell>
                                <TableCell align="center">{row.empno}</TableCell>
                                <TableCell align="center">{row.job}</TableCell>
                                <TableCell align="center">{row.deptno}</TableCell>
                                <TableCell align="center">
                                    {row?.length ?? 0}
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}