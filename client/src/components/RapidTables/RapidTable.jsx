import React, {useState, useEffect, useRef} from 'react';
import Pagination from "./Pagination";
import {utils, rapidTables, _responsive, defaults} from "./RapidTables";
import styles from '../../styles/seperate/rapidTables/ReactTables.module.css';
import Button from "../Button";

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}

const columnDefaults = {
    visible: true,
    hidden: false,
    header: true,
    sortable: true,
    width: '',
    minWidth: 0,
    responsive: {
        type: 'auto',
        priority: 0
    }
};

const rowDefaults = {
    responsive: {
        open: false
    },
    rowIndex: -1,
    details: {}
}

const RapidTable = ({
    data,
    columns,
    pageLengthMenu = [10,25,50,100],
    pagination = true,
    sorting = true,
    search = true,
    rowGroup = false,
    responsive= false
}) => {
    const columnsDefaults = [];
    const rowsDefaults = [];

    rapidTables.handleColumnDefaults(columns, columnDefaults, columnsDefaults);
    rapidTables.handleRowDefaults(data,rowDefaults,rowsDefaults);

    const pageLengthMenuOptions = pageLengthMenu.map((option) => {
        return {
            id: option,
            name: option
        }
    })
    const [sortDirection, setSortDirection] = useState('asc');
    const [sortColumnIndex, setSortColumnIndex] = useState(-1);
    const [searchTerm, setSearchTerm] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [itemsPerPage, setItemsPerPage] = useState(pageLengthMenu[0]);
    const [columnsArray, setColumns] = useState(columnsDefaults);
    const [tableData, setTableData] = useState(rowsDefaults);
    const [expandAll, setExpandAll] = useState(false);
    const tableHeaderRef = useRef(null);
    const tableContainerRef = useRef(null);
    const tableRef = useRef(null);
    const tableBodyRef = useRef(null);
    const visibleColumns = _responsive.handleSetVisibleColumns(columnsArray);
    let className = '';

    useEffect(() => {
        if (tableRef.current === null || tableContainerRef.current === null) {
            return
        }
        const table = tableRef.current;
        const tableContainer = tableContainerRef.current;
        let oldWindowWidth = window.innerWidth;

        rapidTables.handleColumnWidth({
            tableHeader: tableHeaderRef.current,
            columns: columnsArray
        });
        if(responsive !== false) _responsive.handleResponsive({
            table,
            tableContainer,
            tableHeader: tableHeaderRef.current,
            tableBody: tableBodyRef.current,
            columns: columnsArray,
            setColumns,
            tableData,
            setTableData
        });

        function handleWindowResize() {
            const windowWidth = window.innerWidth;

            if(responsive !== false && oldWindowWidth !== windowWidth)
            {
                _responsive.handleResponsive({
                    table,
                    tableContainer,
                    tableHeader: tableHeaderRef.current,
                    tableBody: tableBodyRef.current,
                    columns: columnsArray,
                    setColumns,
                    tableData,
                    setTableData
                });
            }
        }
        const throttleResize = utils.throttle(handleWindowResize);
        window.addEventListener('resize', throttleResize)

        return () => {
            window.removeEventListener('resize', throttleResize)
        }
        // detected rendering
    }, [columnsArray]);
    const filteredData = rapidTables.handleFilterData({dataArray: tableData, searchTerm: searchTerm});
    const sortedData = sortColumnIndex >= 0 ? utils.sortJsonByIndex({array:filteredData, dir:sortDirection, key:columnsArray[columnsArray[0].responsive.type === 'control' && columnsArray[0].visible ? sortColumnIndex : sortColumnIndex + 1].data}) : filteredData;
    const totalItems = tableData.length;
    const totalFilteredItems = sortedData.length;
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = pagination ? sortedData.slice(indexOfFirstItem, indexOfLastItem) : sortedData;
    const totalPages = Math.ceil(totalFilteredItems / itemsPerPage);
    const hasResponsiveDetails = responsive !== false ? _responsive.hasResponsiveDetails(tableData) : false;
    let lastRowGroup = '';

    return (
        <div
            className={"overflow-visible"}
        >
            <div className={"grid justify-items-stretch grid-cols-2"}>
                {pagination &&
                    <div className={"mb-4 max-w-xs"}>
                        
                    </div>
                }
                {search &&
                    <div className={"mb-4 ml-4 max-w-xs justify-self-end"}>
                        <label htmlFor="search" className="sr-only">
                            Search
                        </label>
                        <input
                            className={"block w-full rounded-md border-0 border-neutral-100 py-1.5 px-3 text-neutral-900 shadow-sm ring-1 ring-inset ring-neutral-300 bg-white placeholder:text-neutral-400 focus:ring-2 focus:ring-inset focus:ring-teal-600 sm:text-sm sm:leading-6 dark:bg-neutral-600 dark:border-neutral-800 dark:shadow-none dark:ring-neutral-800 dark:text-neutral-100 dark:placeholder:text-neutral-400"}
                            type="text"
                            name="search"
                            id="search"
                            placeholder="Search..."
                            value={searchTerm}
                            onChange={(event) => rapidTables.handleSearch({
                                event: event,
                                setSearchTerm: setSearchTerm,
                                setCurrentPage: setCurrentPage
                            })}
                        />
                    </div>
                }
                {hasResponsiveDetails &&
                    <div className={classNames(search ? 'col-span-2' : '',"mb-4 ml-4 max-w-xs justify-self-end")}>
                        <Button
                            size="sm"
                            onClick={() => {
                                if(responsive !== false) _responsive.handleExpandAll({
                                    show: !expandAll,
                                    data: tableData,
                                    columns: columnsArray,
                                    setTableData,
                                    setExpandAll
                                });
                            }}
                        >
                            {expandAll ? 'Collapse All' : 'Expand All'}
                        </Button>
                    </div>
                }
            </div>
            <div className="overflow-hidden shadow ring-1 ring-black ring-opacity-5 w-full sm:rounded-lg dark:shadow-neutral-800"
                 ref={tableContainerRef}
            >
                <table
                    className={"min-w-full divide-y divide-neutral-300 dark:divide-neutral-500"}
                    ref={tableRef}
                >
                    <thead
                        className={"bg-neutral-100 tableHeader dark:bg-[#303030]"}
                        ref={tableHeaderRef}
                    >
                        <tr>
                            {visibleColumns.map((column, index) => {
                                className = 'py-3 pl-3 pr-6 text-left text-sm leading-5 font-semibold neutral-900 border-0 relative';
                                className = classNames(
                                    className,
                                    sorting && (column.sortable === undefined || column.sortable) ? styles.sorting : ''
                                )
                                className = classNames(
                                    className,
                                    sorting && (column.sortable === undefined || column.sortable) && index === sortColumnIndex ? styles[sortDirection] : ''
                                )
                                className = classNames(
                                    className,
                                    column.responsive.type
                                )
                                console.log(column)
                                return (
                                    <th
                                        key={index}
                                        onClick={() => {
                                            if(sorting) rapidTables.handleSort({
                                                columnIndex: index,
                                                sortColumnIndex: sortColumnIndex,
                                                sortable: visibleColumns[index].sortable,
                                                sortDirection: sortDirection,
                                                setSortDirection,
                                                setSortColumnIndex
                                            })
                                        }}
                                        className={className}
                                        style={{
                                            width: column.width !== undefined ? column.responsive.type !== 'control' ? column.width + 'px' : '24px' : undefined,
                                            display: !column.header || column.hidden === true ? 'none' : undefined
                                        }}
                                        colSpan={column.colspan}
                                    >
                                        {column.name}
                                    </th>
                                )
                            })}
                        </tr>
                    </thead>
                    <tbody
                        ref={tableBodyRef}
                    >
                        {
                            currentItems.map((item, index) => {
                                const open = currentItems[index].responsive.open;
                                const rowGroupColumn = rowGroup ? typeof rowGroup === "string" ? item[rowGroup] : item[visibleColumns[1].data] : '';
                                const newRowGroup = lastRowGroup !== rowGroupColumn;
                                lastRowGroup = newRowGroup ? rowGroupColumn : lastRowGroup;

                                return (
                                    <React.Fragment key={index}>
                                        {(rowGroup && newRowGroup) &&
                                            <tr className={'bg-neutral-200'}>
                                                <td className={"py-2 px-3 text-sm"} colSpan={visibleColumns.length}>
                                                    {rowGroupColumn}
                                                </td>
                                            </tr>
                                        }
                                        <tr className={(index % 2 === 1 || open ? 'bg-neutral-50 dark:bg-transparent ' : 'dark:bg-neutral-600 ') + (open ? styles.open : '')}>
                                            {visibleColumns.map((column, columnIndex) => {
                                                className = 'p-3 text-left text-sm leading-5 font-light neutral-900 border-0 border-t border-t-neutral-200 dark:border-t-neutral-500';
                                                className = classNames(
                                                    className,
                                                    column.responsive.type === 'control' ? styles.control : column.responsive.type
                                                )
                                                return (
                                                    <td
                                                        key={columnIndex}
                                                        style={{
                                                            width: column.width !== undefined ? column.responsive.type !== 'control' ? column.width + 'px' : '24px' : undefined,
                                                            display: column.hidden === true ? 'none' : undefined
                                                        }}
                                                        className={className}
                                                        width={column.responsive.type === 'control' ? '24px' : undefined}
                                                        onClick={column.responsive.type === 'control' ? () => {
                                                            if(responsive) _responsive.handleDetailsDisplay({
                                                                rowIndex: item.rowIndex,
                                                                data: tableData,
                                                                rowData: currentItems[index],
                                                                columns: columnsArray,
                                                                open: !open,
                                                                setTableData
                                                            })
                                                        } : undefined}
                                                    >
                                                        {item[column.name]}
                                                    </td>
                                                )
                                            })}
                                        </tr>
                                        {open &&
                                            <tr className={"details border-t border-t-neutral-200 dark:bg-neutral-600"}>
                                                <td colSpan={visibleColumns.length}>
                                                    { responsive.render !== undefined ?
                                                        responsive.render({rowIndex: index, details: currentItems[index].responsive.details, columns: visibleColumns})
                                                    :
                                                        <ul className={"details-list mx-5 my-3"}>
                                                            {Object.entries(currentItems[index].responsive.details).map((listItem, index) => {
                                                                return (
                                                                    <li key={index} className={(index % 2 === 0 ? '' : 'bg-neutral-50 dark:bg-neutral-700 ') + ("details-list grid justify-between grid-cols-2 max-w-lg w-full border-neutral-200 p-2 border-b dark:border-neutral-500")}>
                                                                        <span className={"details-title font-normal text-sm"}>{listItem[0]}:</span>
                                                                        <span className={"details-data font-light text-sm"}>{listItem[1]}</span>
                                                                    </li>
                                                                )
                                                            })}
                                                        </ul>
                                                    }
                                                </td>
                                            </tr>
                                        }
                                    </React.Fragment>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
            {pagination &&
                <>
                    <Pagination
                        currentPage={currentPage}
                        totalPages={totalPages}
                        itemsPerPage={itemsPerPage}
                        totalFilteredItems={totalFilteredItems}
                        totalItems={totalItems}
                        onPageChange={(page) => rapidTables.handlePageChange({
                            page: page,
                            setCurrentPage: setCurrentPage
                        })}
                    />
                </>
            }
        </div>
    );
};

export default RapidTable;
