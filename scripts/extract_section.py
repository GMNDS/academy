import re
import os
# Define extract directory
input_file = 'docs/Requisitos/README.md'

# Define the function to extract the section
def extract_section(input_file):
    with open(input_file, 'r', encoding='utf-8') as file:
        content = file.read()

    # Regex to find the section and its content
    pattern_folders = re.compile(r'^##\s([\w\s\-?]+?)\s*$', re.MULTILINE)
    pattern_content = re.compile(r'(##.*?)(?=\n## |\Z)', re.DOTALL)

    # Find the section and its content
    match_folder = pattern_folders.findall(content)
    match_content = pattern_content.findall(content)

    # Get the length of the matches
    match_len = len(match_content)

    # Check if the section and its content were found
    if match_folder and match_content:
        for i in range(match_len):
            section_title = match_folder[i]
            section_content = match_content[i]
            output_file = os.path.join('docs', 'Requisitos', section_title, 'README.md')
            os.makedirs(os.path.dirname(output_file), exist_ok=True)
            with open(output_file, 'w', encoding='utf-8') as file:
                 file.write(section_content)
            print(f'Section "{section_title}" has been extracted to {output_file}')
    else:
        print(f'Section "{section_title}" not found in {input_file}')

# Define the input and output files and the section title






# Extract the section
extract_section(input_file)