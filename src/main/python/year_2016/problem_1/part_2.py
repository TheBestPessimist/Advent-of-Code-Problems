input_data = [['R2', 'L3'],
              ['R2', 'R2', 'R2'],
              ['R5', 'L5', 'R5', 'R3'],
              ['R8', 'R4', 'R4', 'R8'],
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
        self.current_direction = 1
        self.grid_position = [0, 0]
        self.visited_locations = []

    def change_direction(self, turn):
        direction_change = 1 if turn == 'R' else -1
        self.current_direction += direction_change
        if self.current_direction <1:
            self.current_direction = 4
        elif self.current_direction > 4:
            self.current_direction = 1
        elif 1 < self.current_direction > 4:
            raise Exception("WTF is this direction: " + str(self.current_direction))

            # print('direction changed to: ', self.current_direction)

    def move_me(self, step):
        self.change_direction(step[0])

        number_of_steps = int(step[1:])
        for ss in range(number_of_steps):
            if self.current_direction == 1:
                self.grid_position[1] += 1
            elif self.current_direction == 2:
                self.grid_position[0] += 1
            elif self.current_direction == 3:
                self.grid_position[1] -= 1
            elif self.current_direction == 4:
                self.grid_position[0] -= 1

            self.visited_locations.append(list(self.grid_position))
        # print ("le visited: ", self.visited_locations)
            reached_old_location = self.reached_old_location(self.grid_position)
            if reached_old_location:
                return True
        return False

    def reached_old_location(self, current_location):
        # if len(self.visited_locations) != 1:
        for old_location in self.visited_locations[:-1]:
            if current_location == old_location:
                return True
        return False


if __name__ == '__main__':
    for path in input_data:
        print()
        print()

        walker = Walker()
        for step in path:
            reached_old_location = walker.move_me(step)
            if reached_old_location:
                print("reach old location: ", walker.visited_locations)
                break

        print(walker.grid_position)
        print(abs(walker.grid_position[0]) + abs(walker.grid_position[1]))



