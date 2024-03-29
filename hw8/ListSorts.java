/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 100000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue allQueues = new LinkedQueue();
      try{
        while (!(q.isEmpty())){
          LinkedQueue toAdd = new LinkedQueue();
          toAdd.enqueue(q.dequeue());
          allQueues.enqueue(toAdd);
          }
          return allQueues;
      }
      catch (QueueEmptyException e){
          System.err.println(e);
      }
      return allQueues;
      
  }
  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
      LinkedQueue merged = new LinkedQueue();
      
      if (q1.isEmpty() && q2.isEmpty()){
          return merged;
      } 
      
      else{
          try{
            while (!(q1.isEmpty() && q2.isEmpty())){
              if (q1.isEmpty()){
                    while (!(q2.isEmpty())){
                        merged.enqueue(q2.dequeue());
                          } 
                    return merged;
              }    
              else if (q2.isEmpty()){
                    while (!(q1.isEmpty())){
                        merged.enqueue(q1.dequeue());
                    }
                    return merged;
              }
              else {
                  Comparable one =  (Comparable) q1.front();
                  Comparable two =  (Comparable) q2.front();
                  if (one.compareTo(two) > 0){
                      merged.enqueue(q2.dequeue());
                              }
                  else{
                      merged.enqueue(q1.dequeue());
                  }
              }
          }
          return merged;
        }
        catch (QueueEmptyException e){
          System.err.println(e);
      }
          return merged;
      }
  }
  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
      try {
      while (!(qIn.isEmpty())){
          Comparable temp = (Comparable) qIn.dequeue();
          if (temp.compareTo(pivot)< 0){
              qSmall.enqueue(temp);
          }
          else if(temp.compareTo(pivot) == 0){
              qEquals.enqueue(temp);
          }
          else{
              qLarge.enqueue(temp);
          }
      }
      } catch (QueueEmptyException e){
          System.out.println(e);
      }
  }
  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
      try{
      LinkedQueue temp = makeQueueOfQueues(q);
      while (temp.size() > 1){
        LinkedQueue one = (LinkedQueue) temp.dequeue();
        LinkedQueue two = (LinkedQueue) temp.dequeue();
        LinkedQueue toAdd = mergeSortedQueues(one,two);
        temp.enqueue(toAdd);
      }
      while(!(temp.isEmpty())){
        q.append((LinkedQueue) temp.dequeue());
          }
    }
      catch (QueueEmptyException e){
          System.err.println(e);
      }
      return;
  }
  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
      if (q.isEmpty()){
          return;
      }
      int random = (int)(q.size() * Math.random());
      LinkedQueue small = new LinkedQueue();
      LinkedQueue equals = new LinkedQueue();
      LinkedQueue large = new LinkedQueue();
      partition(q, (Comparable) q.nth(random), small, equals, large);
      quickSort(small);
      quickSort(large);
      q.append(small);
      q.append(equals);
      q.append(large);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
