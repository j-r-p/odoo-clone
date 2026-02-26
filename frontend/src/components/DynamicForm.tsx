import React from 'react';
import { useForm } from 'react-hook-form';

interface Field {
  name: string;
  label: string;
  type: string;
}

interface ViewDefinition {
  fields: Field[];
}

interface DynamicFormProps {
  view: ViewDefinition;
  onSubmit: (data: any) => void;
}

const DynamicForm: React.FC<DynamicFormProps> = ({ view, onSubmit }) => {
  const { register, handleSubmit } = useForm();

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
      {view.fields.map((field) => (
        <div key={field.name} className="flex flex-col">
          <label className="mb-1 font-medium">{field.label}</label>
          <input
            {...register(field.name)}
            type={field.type}
            className="border p-2 rounded"
          />
        </div>
      ))}
      <button type="submit" className="bg-blue-500 text-white p-2 rounded">
        Save
      </button>
    </form>
  );
};

export default DynamicForm;
