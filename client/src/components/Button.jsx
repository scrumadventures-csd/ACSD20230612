import React from "react";

const buttonMap = new Map();
      buttonMap.set('primary','bg-teal-700 text-white focus:shadow-focus focus:shadow-teal-300 hover:bg-teal-600 hover:border-teal-600 focus:ring-teal-700');
      buttonMap.set('secondary','bg-gray-500 text-white focus:shadow-focus focus:shadow-gray-400 hover:bg-gray-400 hover:border-gray-400 focus:ring-gray-500');
      buttonMap.set('success','bg-green-600 text-white focus:shadow-focus focus:shadow-green-300 hover:bg-green-500 hover:border-green-500 focus:ring-green-600');
      buttonMap.set('danger','bg-red-600 text-white focus:shadow-focus focus:shadow-red-300 hover:bg-red-500 hover:border-red-500 focus:ring-red-600 dark:bg-red-700 dark:focus:ring-red-700 dark:hover:bg-red-600 dark:hover:border-red-600');
      buttonMap.set('warning','bg-amber-500 text-black focus:shadow-focus focus:shadow-amber-300 hover:bg-amber-400 hover:border-amber-400 focus:ring-amber-500');
      buttonMap.set('info','bg-teal-800 text-white focus:shadow-focus focus:shadow-teal-200 hover:bg-teal-600 hover:border-teal-600 focus:ring-teal-800');
      buttonMap.set('light','bg-gray-100 hover:bg-gray-300 hover:border-gray-300 border-gray-100 text-black focus:shadow-focus focus:shadow-gray-300');
      buttonMap.set('dark','bg-black border-black text-white focus:shadow-focus focus:shadow-gray-400');

const outlineMap = new Map();
      outlineMap.set('primary','text-teal-700 border-teal-700 hover:bg-teal-700 hover:border-teal-700 hover:text-white disabled:hover:text-teal-700 focus:ring-teal-700 dark:text-teal-500 dark:border-teal-500 dark:hover:border-teal-700 dark:hover:text-white');
      outlineMap.set('secondary','text-gray-600 border-gray-500 hover:bg-gray-200 hover:border-gray-400 disabled:hover:text-gray-600 focus:ring-gray-500 dark:text-gray-300 dark:hover:text-gray-600 dark:border-gray-400 dark:hover:bg-gray-100 dark:hover:border-gray-300');
      outlineMap.set('success','text-green-600 border-green-600 hover:bg-green-600 hover:border-green-600 hover:text-white disabled:hover:text-green-600 focus:ring-green-600');
      outlineMap.set('danger','text-red-600 border-red-600 hover:bg-red-600 hover:border-red-600 hover:text-white disabled:hover:text-red-600 focus:ring-red-600');
      outlineMap.set('warning','text-amber-500 border-amber-500 hover:bg-amber-500 hover:border-amber-500 hover:text-black disabled:hover:text-amber-500 focus:ring-amber-500');
      outlineMap.set('info','text-teal-800 border-teal-800 hover:bg-teal-600 hover:border-teal-600 hover:text-white disabled:hover:text-teal-600 focus:ring-teal-800 dark:text-teal-600 dark:hover:text-white dark:border-teal-600 dark:hover:bg-teal-500 dark:hover:border-teal-500');
      outlineMap.set('light','text-gray-100 hover:bg-gray-300 hover:border-gray-300 hover:text-black disabled:hover:text-gray-100');
      outlineMap.set('dark','text-black hover:bg-black hover:border-black hover:text-white disabled:hover:text-black');

function classNames(...classes) {
    return classes.filter(Boolean).join(' ')
}

const Button = ({
    size = 'md',
    theme = 'primary',
    type = 'button',
    outline = false,
    className = '',
    rounded = false,
    circle = false,
    onClick,
    children
}) => {
    const sizeMap = new Map();
        sizeMap.set('xs', circle ? 'p-1 text-xs' : 'px-2.5 py-1.5 text-xs');
        sizeMap.set('sm', circle ? 'p-1.5 text-sm' : 'px-3 py-2 text-sm leading-4');
        sizeMap.set('md', circle ? 'p-2 text-base' : 'px-4 py-2 text-base');
        sizeMap.set('lg', circle ? 'p-2 text-lg' : 'px-4 py-2 text-lg');
        sizeMap.set('xl', circle ? 'p-3 text-xl' : 'px-6 py-3 text-xl');

    const iconLeadingSizeMap = new Map();
          iconLeadingSizeMap.set('xs', 'mr-2');
          iconLeadingSizeMap.set('sm', 'mr-2');
          iconLeadingSizeMap.set('md', 'mr-3');
          iconLeadingSizeMap.set('lg', 'mr-3');
          iconLeadingSizeMap.set('xl', 'mr-3');

    const iconTrailingSizeMap = new Map();
          iconTrailingSizeMap.set('xs', 'ml-2');
          iconTrailingSizeMap.set('sm', 'ml-2');
          iconTrailingSizeMap.set('md', 'ml-3');
          iconTrailingSizeMap.set('lg', 'ml-3');
          iconTrailingSizeMap.set('xl', 'ml-3');

    const style = (outline ? " bg-transparent disabled:bg-transparent " + outlineMap.get(theme) : ' border-transparent ' + buttonMap.get(theme));
    let classes = classNames(
        className,
        'inline-flex items-center border font-medium shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2'
    );

    classes = classNames(
        style,
        classes
    );

    classes = classNames(
        sizeMap.get(size),
        classes
    );

    classes = classNames(
        rounded || circle ? 'rounded-full' : 'rounded',
        classes
    );

    return (
        <button
            type={type}
            className={classes}
            onClick={onClick}
        >
            {children}
        </button>
    );
};

export default Button;