package tangible;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class GlobalMembers
{
	public static final int MAX_SIZE = 10000000;
	public static String sortAlg= null;
	public static int qLeft=0;
	public static int qRight=0;
	public static int depth=0;

	// Set this to true if you wish the arrays to be printed.
	public static final boolean OUTPUT_DATA = false;;
/**
 * @throws IOException ******************************************************************/


	public static void ReadInput(tangible.RefObject<Integer> size) throws IOException
	{
		System.out.print("  I:\tInsertion Sort");
		System.out.print("\n");
		System.out.print("  M:\tMergeSort");
		System.out.print("\n");
		System.out.print("  Q:\tQuickSort");
		System.out.print("\n");
		System.out.print("  S:\tSTLSort");
		System.out.print("\n");
		System.out.print("Enter sorting algorithm: ");
		sortAlg = ConsoleInput.readToWhiteSpace(true);
		String sortAlgName = null;


		if (sortAlg.equals("I"))
		{
			sortAlgName = "Insertion Sort";
		}
		else if (sortAlg.equals("M"))
		{
			sortAlgName = "MergeSort";
		}
		else if (sortAlg.equals("Q"))
		{
			sortAlgName = "QuickSort";
		}
		else if (sortAlg.equals("S"))
		{
			sortAlgName = "STLSort";
		}
		else
		{
			System.out.print("\nUnrecognized sorting algorithm Code: ");
			System.out.print(sortAlg);
			System.out.print("\n");
			System.exit(1);
		}

		System.out.print("Enter input size: ");
		size.argValue = Integer.parseInt(ConsoleInput.readToWhiteSpace(true));

		System.out.print("\nSorting Algorithm: ");
		System.out.print(sortAlgName);
		System.out.print("\nInput Size = ");
		System.out.print(size.argValue);
		System.out.print("\n");
		System.out.print("\n");

	}
/******************************************************************************/





	public static void GenerateSortedData(int[] data, int size)
	{
		int i;
		System.out.println("SortedData");

		for (i = 0; i < size; i++)
		{
			data[i] = i * 3 + 5;
		}
	}
/*****************************************************************************/



	public static void GenerateNearlySortedData(int[] data, int size)
	{
		int i;
		System.out.println("NearlySorteData");

		GenerateSortedData(data, size);

		for (i = 0; i < size; i++)
		{
			if (i % 10 == 0)
			{
				if (i + 1 < size)
				{
					data[i] = data[i + 1] + 7;
				}
			}
		}
	}
/*****************************************************************************/



	public static void GenerateReverselySortedData(int[] data, int size)
	{
		int i;
		System.out.println("ReverslySortedData");

		for (i = 0; i < size; i++)
		{
			data[i] = (size - i) * 2 + 3;
		}
	}
/*****************************************************************************/



	public static void GenerateRandomData(int[] data, int size)
	{
		int i;
		System.out.println("RandomData");

		for (i = 0; i < size; i++)
		{
			data[i] = tangible.RandomNumbers.nextNumber();
		}
	}
/*****************************************************************************/



	public static void Sort(int[] data, int size, String sortAlg, String string)
	{

		System.out.print("\n");
		System.out.print(":");


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data before sorting:");
		}



		// Sorting is about to begin ... start the timer!
		long startTime = System.nanoTime();

		if (sortAlg.equals("I"))
		{
			InsertionSort(data, size);
		}
		else if (sortAlg.equals("M"))
		{
			MergeSort(data, 0, size-1);
		}
		else if (sortAlg.equals("Q"))
		{
			QuickSort(data, 0, size-1);
			System.out.println(depth);
		}
		else if (sortAlg.equals("S"))
		{
			STLSort(data, size);
		}
		else
		{
			System.out.print("Invalid sorting algorithm!");
			System.out.print("\n");
			System.exit(1);
		}

		// Sorting has finished ... stop the timer!
		long endTime = System.nanoTime();
		long elapsed = (((endTime - startTime)/1000000));


		if (OUTPUT_DATA)
		{
			printData(data, size, "Data after sorting:");
		}


		if (IsSorted(data, size))
		{
			System.out.print("\nCorrectly sorted ");
			System.out.print(size);
			System.out.print(" elements in ");
			System.out.print(elapsed);
			System.out.print("ms");
		}
		else
		{
			System.out.print("ERROR!: INCORRECT SORTING!");
			System.out.print("\n");
		}
		System.out.print("\n-------------------------------------------------------------\n");
	}
/*****************************************************************************/





	public static void InsertionSort(int[] data, int size)
	
	{
		for(int i =1; i <data.length;i++)
		{	
			int temp = data[i];
			int in =i;
			while(in >0 && data[in-1]> temp)
			{
				data[in]= data[in-1];
				in = in-1;
			}
			data[in] = temp;
			
		}
	}
/*****************************************************************************/


	public static void MergeSort(int[] data, int lo, int hi)
	{
		int q=0;
		if(lo<hi)
		{
			q=(lo+hi)/2;		
		  MergeSort(data,lo,q);
		  MergeSort(data,q+1,hi);
		  Merge(data,lo,hi,q);
		}
		//Write your code here
		//You may create other functions if needed
	}
	
	
	private static void Merge(int[] data, int low, int high, int mid) 
	{
		int MAX;
		int i=0, j=0;
		int k=0;
		int n1= mid-low+1;
		int n2= high-mid;
		int[] Left= new int[n1+1];
		int[] Right= new int[n2+1];
		for(i=0; i<n1;i++)
			Left[i] = data[low+i];
		for(j=0; j<n2;j++)
			Right[j] = data[mid+1+j];
		MAX = Math.max(Left[n1-1], Right[n2-1]);
		Left[n1]= MAX;
		Right[n2]=MAX;
		k=low;
		for(i=0,j=0;k<=high;k++)
		{
			if(Left[i]< Right[j])
			{
				data[k]=Left[i];
				i++;
			}
			else
			{
				data[k]=Right[j];
				j++;
			}
		
		}
	}
/*****************************************************************************/




	public static int QuickSort(int[] data, int lo, int hi)
	{
		int qLeft =0;
		int qRight =0;
		if(lo >= hi)
			return 0;
		//Run insertion sort for inputs less than 30 for sum 2(3)
/*		if(data.length <30)
		{
			InsertionSort(data,data.length);
			return 1;
		}*/
		int q= Partition(data,lo,hi);
		//int q=MedianPartition(data,lo,hi);
		//int q = Median_Partition(data,lo,hi);
		qLeft= 1+ QuickSort(data, lo, q-1);	
		qRight= 1+ QuickSort(data, q+1, hi);
		if(qLeft>qRight)
		{
		depth=qLeft;
		return qLeft;
		}
		else 
		{
		depth=qRight;
		return qRight;
		}


	}
	

	//Simple randomized partititon for sum2(1)
	public static int Partition(int[] data, int lo, int hi)
	{
	int range = (hi-lo) +1;
	int random = (int) ((Math.random()* range)+lo);
	swap(random,hi,data);
	int j=lo-1;
	for(int i=lo; i<hi;i++)
	{
		if(data[i]< data[hi])
				{
				swap(i,j+1,data);
				j++;
				}
	
	}
	swap(hi,j+1,data);
	return j+1;
	}
	//Median Partition for sum 2(2)
	public static int MedianPartition(int [] data,int lo,int hi)
	{
		int range = (hi-lo)+1;
		int []numbers = new int[3];
		int [] indexes = new int[3];
		int temp;int medianIndex=0;
		for(int i =0;i<3;i++)
		{ 
			temp =(int)(Math.random()*range)+lo;
			numbers[i]= data[ temp];
			indexes[i] = temp;
			
		}

		int median = Math.max(Math.min(numbers[0],numbers[1]), Math.min(Math.max(numbers[0],numbers[1]),numbers[2]));//took this from stack overflow
		for(int i =0;i <3;i++)
		{
		if(median== numbers[i]){
			medianIndex = indexes[i];
		}
		}
		swap(medianIndex,hi,data);
		int j=lo-1;
		for(int i=lo; i<hi;i++)
		{
			if(data[i]< data[hi])
					{
					swap(i,j+1,data);
					j++;
					}
		
		}
		swap(hi,j+1,data);
		return j+1;
	}
	
	public static void swap(int a,int b,int[] data)
	{
		int temp= data[a];
		data[a]=data[b];
		data[b]=temp;	
	}

	public static int Median_Partition(int[] data,int low,int high)
	{
		int median=Median(data, low,high);
		int j=low-1;
		swap(median,high,data);
		for(int i=low; i<high;i++)
		{
			if(data[i]< data[high])
					{
					swap(i,j+1,data);
					j++;
					}
		
		}
		swap(high,j+1,data);
		return j+1;
		}
		
	
	public static int Median(int [] data,int low,int high)
	{
		int mid = (low + high)/2;
			    if (data[high] < data[low])
			        swap(low,high,data) ;       
			    if (data[mid] < data[low])
			        swap( mid, low,data);
			    if (data[high] < data[mid])
			        swap(high, mid,data);
			    return mid;
	}
/*****************************************************************************/



	public static void STLSort(int[] data, int size)
	{
		Arrays.sort(data);

	}
/*****************************************************************************/





	public static void Swap(tangible.RefObject<Integer> x, tangible.RefObject<Integer> y)
	{
		int temp = x.argValue;
		x.argValue = y.argValue;
		y.argValue = temp;
	}
/*****************************************************************************/





	public static boolean IsSorted(int[] data, int size)
	{
		int i;

		for (i = 0; i < (size-1); i++)
		{
			if (data[i] > data[i + 1])
			{
				return false;
			}
		}
		return true;
	}
/*****************************************************************************/




	public static void printData(int[] data, int size, String title)
	{
		int i;

		System.out.print("\n");
		System.out.print(title);
		System.out.print("\n");
		for (i = 0; i < size; i++)
		{
			System.out.print(data[i]);
			System.out.print(" ");
			if (i % 10 == 9 && size > 10)
			{
				System.out.print("\n");
			}
		}
	}


	public static void main(String args[]) throws IOException
	{
		int size = 0;
	tangible.RefObject<Integer> tempRef_size = new tangible.RefObject<Integer>(size);
		ReadInput(tempRef_size);
		size = tempRef_size.argValue;

		int[] data = new int[size];
		if (data == null)
		{
			System.out.print("\n Memory allocation error.");
			System.out.print("\n");
			System.exit(1);
		}

		GenerateSortedData(data, size);
		Sort(data, size, sortAlg, "Sorted Data");

		GenerateNearlySortedData(data, size);
		Sort(data, size, sortAlg, "Nearly Sorted Data");

		GenerateReverselySortedData(data, size);
		Sort(data, size, sortAlg, "Reversely Sorted Data");

		GenerateRandomData(data, size);
		Sort(data, size, sortAlg, "Random Data");

		System.out.print("\nProgram Completed Successfully.");
		System.out.print("\n");

		data = null;

	}
}