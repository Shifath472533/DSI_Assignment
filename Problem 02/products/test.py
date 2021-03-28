studentList = []
studentList.append({'name': "Shihab", 'roll': '02', 'code': 'shib'})
studentList.append({'name': "Rakib", 'roll': '05', 'code': 'rak'})
studentList.append({'name': "Rafi", 'roll': '03', 'code': 'raf'})
studentList.append({'name': "Shifath", 'roll': '01', 'code': 'shif'})
studentList.append({'name': "Sowmen", 'roll': '04', 'code': 'sow'})


print(studentList)

newlist = sorted(studentList, key=lambda k: k['roll'])

print(newlist)