import React from 'react';
import styles from '../../styles/seperate/rapidTables/Pagination.module.css';

function classNames(...classes ) {
    return classes.filter(Boolean).join(' ')
}

const Pagination = ({
    currentPage,
    totalPages,
    itemsPerPage,
    totalItems,
    totalFilteredItems,
    onPageChange,
}) => {
    const handleFirstPageClick = () => {
        onPageChange(1);
    };

    const handlePrevPageClick = () => {
        onPageChange(currentPage - 1);
    };

    const handleNextPageClick = () => {
        onPageChange(currentPage + 1);
    };

    const handleLastPageClick = () => {
        onPageChange(totalPages);
    };

    const isFirstPage = currentPage === 1;
    const isLastPage = currentPage === totalPages;

    const pageButtons = [];

    let startPage = currentPage - 2;
    let endPage = currentPage + 3;

    if (startPage < 1) {
        startPage = 1;
        endPage = Math.min(startPage + 5, totalPages);
    }

    if (endPage > totalPages) {
        endPage = totalPages;
        startPage = Math.max(endPage - 5, 1);
    }

    for (let i = startPage; i <= endPage; i++) {
        pageButtons.push(
            <button
                className={classNames(
                    "-ml-[1px] inline-flex text-center py-2 px-3 text-sm font-normal shadow-[#fff_0_0_0_0_inset,#d4d4d4_0_0_0_1px_inset,#000_0_0_0_0] cursor-pointer disabled:cursor-not-allowed dark:shadow-[#000_0_0_0_0_inset,#404040_0_0_0_1px_inset,#000_0_0_0_0] dark:text-neutral-200",
                    i === currentPage ? 'bg-teal-700 text-neutral-50 dark:bg-teal-700' : 'bg-white hover:bg-teal-600 hover:text-neutral-50 dark:bg-neutral-500 dark:hover:bg-teal-600'
                )}
                key={i}
                onClick={() => onPageChange(i)}
                disabled={i === currentPage}
            >
                {i}
            </button>
        );
    }
    const filteredFrom = totalFilteredItems !== totalItems ? '(filtered from '+ totalItems +' total)' : '';

    return (
        <div className={"flex flex-col items-center justify-between mt-6 md:flex-row"}>
            <div className={"inline-flex text-sm font-light text-neutral-800 dark:text-neutral-300 mb-4 md:mb-0"}>
                <span className={"align-sub"}>{'Showing '+ ((itemsPerPage * currentPage) - (itemsPerPage - 1)) + ' to '+ (itemsPerPage * currentPage <= totalFilteredItems ? itemsPerPage * currentPage : totalFilteredItems) +' of '+ totalFilteredItems + ' entries '+ filteredFrom}</span>
            </div>
            <div className={"inline-flex rounded shadow-sm dark:shadow-neutral-700"}>
                <button
                    className={"ml-0 rounded-l inline-flex text-center bg-white py-2 px-3 text-sm font-normal text-neutral-900 shadow-[#000_0_0_0_0_inset,#d4d4d4_0_0_0_1px_inset,#000_0_0_0_0] cursor-pointer hover:bg-teal-600 hover:text-neutral-50 disabled:bg-neutral-200 hover:disabled:bg-neutral-200 hover:disabled:text-neutral-900 disabled:cursor-not-allowed dark:bg-neutral-500 dark:text-neutral-200 dark:disabled:bg-neutral-600 dark:disabled:text-neutral-500 dark:shadow-[#000_0_0_0_0_inset,#404040_0_0_0_1px_inset,#000_0_0_0_0]"}
                    disabled={isFirstPage}
                    onClick={handleFirstPageClick}
                >
                    First
                </button>
                <button
                    className={"-ml-[1px] inline-flex text-center bg-white py-2 px-3 text-sm font-normal text-neutral-900 shadow-[#fff_0_0_0_0_inset,#d4d4d4_0_0_0_1px_inset,#000_0_0_0_0] cursor-pointer hover:bg-teal-600 hover:text-neutral-50 disabled:bg-neutral-200 hover:disabled:bg-neutral-200 hover:disabled:text-neutral-900 disabled:cursor-not-allowed dark:bg-neutral-500 dark:text-neutral-200 dark:disabled:bg-neutral-600 dark:disabled:text-neutral-500 dark:shadow-[#000_0_0_0_0_inset,#404040_0_0_0_1px_inset,#000_0_0_0_0]"}
                    disabled={isFirstPage}
                    onClick={handlePrevPageClick}
                >
                    Previous
                </button>
                {pageButtons}
                <button
                    className={"-ml-[1px] inline-flex text-center bg-white py-2 px-3 text-sm font-normal text-neutral-900 shadow-[#fff_0_0_0_0_inset,#d4d4d4_0_0_0_1px_inset,#000_0_0_0_0] cursor-pointer  hover:bg-teal-600 hover:text-neutral-50 disabled:bg-neutral-200 hover:disabled:bg-neutral-200 hover:disabled:text-neutral-900 disabled:cursor-not-allowed dark:bg-neutral-500 dark:text-neutral-200 dark:disabled:bg-neutral-600 dark:disabled:text-neutral-500 dark:shadow-[#000_0_0_0_0_inset,#404040_0_0_0_1px_inset,#000_0_0_0_0] dark:hover:bg-teal-600"}
                    disabled={isLastPage}
                    onClick={handleNextPageClick}
                >
                    Next
                </button>
                <button
                    className={"-ml-[1px] rounded-r inline-flex text-center bg-white py-2 px-3 text-sm font-normal text-neutral-900 shadow-[#fff_0_0_0_0_inset,#d4d4d4_0_0_0_1px_inset,#000_0_0_0_0] cursor-pointer  hover:bg-teal-600 hover:text-neutral-50 disabled:bg-neutral-200 hover:disabled:bg-neutral-200 hover:disabled:text-neutral-900 disabled:cursor-not-allowed dark:bg-neutral-500 dark:text-neutral-200 dark:disabled:bg-neutral-600 dark:disabled:text-neutral-500 dark:shadow-[#000_0_0_0_0_inset,#404040_0_0_0_1px_inset,#000_0_0_0_0] dark:hover:bg-teal-600"}
                    disabled={isLastPage}
                    onClick={handleLastPageClick}
                >
                    Last
                </button>
            </div>
        </div>
    );
};

export default Pagination;
