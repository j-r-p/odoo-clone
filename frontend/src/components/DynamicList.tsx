import React from 'react';

interface Column {
  name: string;
  label: string;
}

interface ViewDefinition {
  columns: Column[];
}

interface DynamicListProps {
  view: ViewDefinition;
  data: any[];
}

const DynamicList: React.FC<DynamicListProps> = ({ view, data }) => {
  return (
    <table className="min-w-full border-collapse border border-gray-200">
      <thead>
        <tr>
          {view.columns.map((col) => (
            <th key={col.name} className="border p-2 bg-gray-100">
              {col.label}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((row, index) => (
          <tr key={index} className="hover:bg-gray-50">
            {view.columns.map((col) => (
              <td key={col.name} className="border p-2">
                {row[col.name]}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default DynamicList;
