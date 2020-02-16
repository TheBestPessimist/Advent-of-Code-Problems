input_data = [['R2', 'L3'],
              ['R2', 'R2', 'R2'],
              ['R5', 'L5', 'R5', 'R3'],
              ['R4', 'R5', 'L5', 'L5', 'L3', 'R2', 'R1', 'R1', 'L5', 'R5', 'R2', 'L1', 'L3', 'L4', 'R3', 'L1', 'L1',
               'R2', 'R3', 'R3', 'R1', 'L3', 'L5', 'R3', 'R1', 'L1', 'R1', 'R2', 'L1', 'L4', 'L5', 'R4', 'R2', 'L192',
               'R5', 'L2', 'R53', 'R1', 'L5', 'R73', 'R5', 'L5', 'R186', 'L3', 'L2', 'R1', 'R3', 'L3', 'L3', 'R1', 'L4',
               'L2', 'R3', 'L5', 'R4', 'R3', 'R1', 'L1', 'R5', 'R2', 'R1', 'R1', 'R1', 'R3', 'R2', 'L1', 'R5', 'R1',
               'L5', 'R2', 'L2', 'L4', 'R3', 'L1', 'R4', 'L5', 'R4', 'R3', 'L5', 'L3', 'R4', 'R2', 'L5', 'L5', 'R2',
               'R3', 'R5', 'R4', 'R2', 'R1', 'L1', 'L5', 'L2', 'L3', 'L4', 'L5', 'L4', 'L5', 'L1', 'R3', 'R4', 'R5',
               'R3', 'L5', 'L4', 'L3', 'L1', 'L4', 'R2', 'R5', 'R5', 'R4', 'L2', 'L4', 'R3', 'R1', 'L2', 'R5', 'L5',
               'R1', 'R1', 'L1', 'L5', 'L5', 'L2', 'L1', 'R5', 'R2', 'L4', 'L1', 'R4', 'R3', 'L3', 'R1', 'R5', 'L1',
               'L4', 'R2', 'L3', 'R5', 'R3', 'R1', 'L3']
              ]
class Walker:
    """
    directions:
        1 = N
        2 = E
        3 = S
        4 = W


    grid_position has format [x,y]
    """

    def __init__(self):
        self.current_direction = 0
        self.grid_position = [0, 0]

    def change_direction(self, turn):
        direction_change = 1 if turn == 'R' else -1
        self.current_direction += direction_change
        if self.current_direction == 0:
            self.current_direction = 4
        elif self.current_direction == 5:
            self.current_direction = 1
        elif 0 < self.current_direction > 5:
            raise Exception("WTF is this direction: " + str(self.current_direction))

        # print('direction changed to: ', self.current_direction)

    def move_me(self, step):
        self.change_direction(step[0])

        number_of_steps = int(step[1:])

        if self.current_direction == 1:
            self.grid_position[1] += number_of_steps
        elif self.current_direction == 2:
            self.grid_position[0] += number_of_steps
        elif self.current_direction == 3:
            self.grid_position[1] -= number_of_steps
        elif self.current_direction == 4:
            self.grid_position[0] -= number_of_steps

        # print('grid_position changed to: ', self.grid_position)


if __name__ == '__main__':
    for path in input_data:
        print()
        print()

        walker = Walker()
        for step in path:
            walker.move_me(step)

        print(walker.grid_position)
        print(abs(walker.grid_position[0]) + abs(walker.grid_position[1]))
