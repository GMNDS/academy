import re

def extract_section(input_file, output_file, section_title):
    with open(input_file, 'r', encoding='utf-8') as file:
        content = file.read()

    # Regex to find the section and its content
    pattern = re.compile(rf'({section_title}.*?)(?=\n## |\Z)', re.DOTALL)
    match = pattern.search(content)

    if match:
        section_content = match.group(1)
        with open(output_file, 'w', encoding='utf-8') as file:
            file.write(section_content)
        print(f'Section "{section_title}" has been extracted to {output_file}')
    else:
        print(f'Section "{section_title}" not found in {input_file}')

# Define the input and output files and the section title
input_file = 'docs/README.md'
output_file = 'docs/Requisitos/Professor/README.md'
section_title = '## Professor'

# Extract the section
extract_section(input_file, output_file, section_title)