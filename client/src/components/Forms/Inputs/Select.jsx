import {Fragment, useState} from 'react';
import {Listbox, Transition} from '@headlessui/react';
import {CheckIcon, ChevronUpDownIcon} from '@heroicons/react/20/solid';

interface selectLabelProps {
    text: string;
    visible: boolean;
}

interface selectDataProps {
    name: string | number;
    id: string | number;
}

interface selectProps {
    data: Array<selectDataProps>,
    value: any;
    label: any,
    onChange: any;
}

function classNames(...classes : any) {
    return classes.filter(Boolean).join(' ')
}

export default function Select({
    data,
    value,
    label,
    onChange
} : selectProps) {
    let selectedOption: any = data.filter((option,index) => {
        return option.id === value;
    });
    selectedOption = selectedOption.length > 0 ? selectedOption[0] : data[0];
    const [selected, setSelected] = useState(selectedOption)
    return (
        <Listbox value={selected} onChange={(option) => {
            setSelected(option);
            if(onChange !== undefined) onChange(option);
        }}>
            {({ open }) => (
                <>
                    <Listbox.Label className={classNames(
                        label.visible ? '' : 'sr-only',
                        "block text-sm font-medium leading-6 text-neutral-900"
                    )}>{label.text}</Listbox.Label>
                    <div className="relative">
                        <Listbox.Button className="relative w-full cursor-default rounded-md bg-white py-1.5 pl-3 pr-10 text-left text-neutral-900 shadow-sm ring-1 ring-inset ring-neutral-300 focus:outline-none focus:ring-2 focus:ring-teal-600 sm:text-sm sm:leading-6 dark:bg-neutral-600 dark:shadow-none dark:ring-neutral-800 dark:text-neutral-200">
                            <span className="block">{selected.name}</span>
                            <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                                <ChevronUpDownIcon className="h-5 w-5 text-neutral-400" aria-hidden="true" />
                            </span>
                        </Listbox.Button>

                        <Transition
                            show={open}
                            as={Fragment}
                            leave="transition ease-in duration-100"
                            leaveFrom="opacity-100"
                            leaveTo="opacity-0"
                        >
                            <Listbox.Options className="absolute z-10 mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm dark:bg-neutral-600">
                                {data.map((item) => (
                                    <Listbox.Option
                                        key={item.id}
                                        className={({ active }) =>
                                            classNames(
                                                active ? 'bg-teal-600 text-white dark:text-neutral-100 dark:bg-teal-700' : 'text-neutral-900 dark:text-neutral-100',
                                                'relative cursor-default select-none py-2 pl-3 pr-9'
                                            )
                                        }
                                        value={item}
                                    >
                                        {({ active }) => {
                                            const optionSelected = selected.id === item.id;
                                            return (
                                                <>
                                                    <span className={classNames(optionSelected ? 'font-semibold' : 'font-normal', 'block')}>
                                                      {item.name}
                                                    </span>
                                                    {optionSelected ? (
                                                        <span
                                                            className={classNames(
                                                                active ? 'text-white' : 'text-teal-600 dark:text-teal-400',
                                                                'absolute inset-y-0 right-0 flex items-center pr-4'
                                                            )}
                                                        >
                                                            <CheckIcon className="h-5 w-5" aria-hidden="true" />
                                                        </span>
                                                    ) : null}
                                                </>
                                            )
                                        }}
                                    </Listbox.Option>
                                ))}
                            </Listbox.Options>
                        </Transition>
                    </div>
                </>
            )}
        </Listbox>
    )
}
