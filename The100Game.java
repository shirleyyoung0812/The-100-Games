import java.util.*;


public class The100Game {
	public void canIWin(int maxChooseableInteger, int desiredTotal) {
		if ((maxChooseableInteger*maxChooseableInteger + maxChooseableInteger) / 2 < desiredTotal) {
			throw new IllegalArgumentException("Wrong input!");
		}
		boolean[] pool = new boolean[maxChooseableInteger];
		for (int i = 0; i < maxChooseableInteger; i++) {
			System.out.println("If I start with: " + (i + 1));
			pool[i] = true;
			canP1Win(i + 1, i + 1, maxChooseableInteger, desiredTotal, pool, true);
				System.out.println("*******************");	
			pool = new boolean[maxChooseableInteger];
		}
	}
	boolean canP1Win(int lastNumber, int currentSum, int maxChooseableInteger,
			int total, boolean[] pool, boolean isLastP1) {
		if (currentSum >= total) {
			if (isLastP1) 
				System.out.println("I can win!");
			else
				System.out.println("I will lose... :(");
			return isLastP1;
		}
		boolean isCurrentPlayerP1 = !isLastP1;
		int index;
		if (total - currentSum <= maxChooseableInteger) {
			index = maxChooseableInteger - 1;
			while (index >= 0 && pool[index]) {
				index--;
			}
			if (index < 0)
				return isCurrentPlayerP1;
			if ((currentSum + index + 1) >= total) {
				if (isCurrentPlayerP1) 
					System.out.println("I can win with: " + (index + 1));
				else
					System.out.println("I will lose with: " + (index + 1) + " :'(");
				return isCurrentPlayerP1;
			}
			//if the current player cannot win, she will choose a strategy not to let the opponent win
			index = 0;
			while (pool[index]) {
				index++;
			}	
		}
		else {
			Random r = new Random();
			if (lastNumber <= maxChooseableInteger / 2){
				index = r.nextInt(maxChooseableInteger / 2 + 1) + maxChooseableInteger/2;
				int count = 0;
				while ((pool[index] || index > maxChooseableInteger - 1 ) && count <= maxChooseableInteger) {
					index = r.nextInt(maxChooseableInteger / 2 + 1) + maxChooseableInteger/2;
					count++;
				}
				while (index >= 0 && pool[index]) {
					index--;
				}
				if (index < 0)
					return isCurrentPlayerP1;
			}
			else {
				index = r.nextInt(maxChooseableInteger / 2);
				int count = 0;
				while(pool[index] && count <= maxChooseableInteger) {
					index = r.nextInt(maxChooseableInteger / 2);
				}
				while (pool[index]) {
					index++;
				}
				//System.out.println("3 index: " + (index + 1));
			}	
		}
		if (isCurrentPlayerP1)
			System.out.println("I will choose " + (index + 1));
		else
			System.out.println("The oponent chooses: " + (index + 1));
		pool[index] = true;
		boolean p1CanWin = canP1Win(index + 1, currentSum + index + 1, maxChooseableInteger, total, 
				pool, isCurrentPlayerP1);
		if (isCurrentPlayerP1 && p1CanWin)
			return true;
		if (!isCurrentPlayerP1 && !p1CanWin)
			return false;
		pool[index] = false;
		return isCurrentPlayerP1 ? false : true;
	}
}
