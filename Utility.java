import java.util.*;

public class Utility {

  public static void main(String[] args) {

  }

  // if ax + by = c then Let g = gcd(a, b) then there exists integers x, y such
  // that ax + by = g. Therefore c % g == 0, for the above conditions.

  // we need to find the value of x and y then the formula is (g = gcd(a, b))
  // (only one solution)
  // x => (c / g) * (a / g) ^ -1 * (mod b / g)
  // y => (c - ax) / g.

  /*
   * BINARY SEARCH TIP
   * Collections.binarySearch(list, a[i])
   * If the a[i] value not found in the list then it return
   * negative value --> { -(insertion point) - 1 }
   * long idx = Arrays.binarySearch(b, stu);
   * if (idx < 0) {
   * idx = -(idx + 1);
   * Adjust for the negative index from binarySearch
   * }
   * 
   * for calculating the mex
   * int[] leftMex = new int[n];
   * boolean[] present = new boolean[n + 1];
   * int currentMex = 0;
   * for (int i = 0; i < n; i++) {
   * present[a[i]] = true;
   * while (currentMex <= n && present[currentMex]) {
   * currentMex++;
   * }
   * leftMex[i] = currentMex;
   * }
   * -----------------------------------------------------
   * 
   */

  static class Pair {
    int first, second;

    Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this)
        return true;
      if (!(obj instanceof Pair))
        return false;
      Pair pair = (Pair) obj;
      return pair.first == this.first && pair.second == this.second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }
  }

  private static long maxSubarraySum(long[] a, int left, int right) {
    long curr = 0, maxSum = 0;
    for (int i = left; i <= right; i++) {
      curr += a[i];
      maxSum = Math.max(maxSum, curr);
      if (curr < 0) {
        curr = 0;
      }
    }
    return maxSum;
  }

  private static long minSubarraySum(long[] a, int left, int right) {
    long curr = 0, maxSum = 0;
    for (int i = left; i <= right; i++) {
      curr -= a[i];
      maxSum = Math.max(maxSum, curr);
      if (curr < 0) {
        curr = 0;
      }
    }
    return -maxSum;
  }

  private static int lowerBound(long[] arr, long key) {
    int lo = 0, hi = arr.length;
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (arr[mid] >= key) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }

  private static int lowerBound(long[] a, int start, int end, long val) {
    int lo = start, hi = end, res = end + 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (a[mid] >= val) {
        res = mid;
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return res;
  }

  private static int lowerBound(List<Long> a, long value) {
    int left = 0, right = a.size();
    while (left < right) {
      int mid = (left + right) / 2;
      if (a.get(mid) < value) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }

  private static int upperBound(long[] a, int start, int end, long val) {
    int lo = start, hi = end, res = start - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (a[mid] <= val) {
        res = mid;
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return res;
  }

  static long modDiv(long x, long y, long mod) {
    // x * y^(MOD-2) % MOD
    return (x * modPow(y, mod - 2, mod)) % mod;
  }

  static long modPow(long base, long exp, long mod) {
    long result = 1;
    base = base % mod;
    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = (result * base) % mod;
      }
      base = (base * base) % mod;
      exp >>= 1;
    }
    return result;
  }

  public static long nCr(int n, int r) {
    if (r > n)
      return 0;
    if (r == 0 || r == n)
      return 1;
    r = Math.min(r, n - r);
    long result = 1;
    for (int i = 0; i < r; i++) {
      result = (result * (n - i)) % MOD;
      result = (modDiv(result, (i + 1), MOD));
    }
    return result;
  }

  static long binpow(long a, long b) {
    long res = 1;
    while (b > 0) {
      if ((b & 1) == 1)
        res = res * a;
      a = a * a;
      b >>= 1;
    }
    return res;
  }

  static long binPowMod(long a, long b, long MOD) {
    long result = 1;
    a = a % MOD; // in case 'a' is larger than MOD

    while (b > 0) {
      if ((b & 1) == 1) {
        result = (result * a) % MOD;
      }
      a = (a * a) % MOD;
      b >>= 1;
    }
    return result;
  }

  static void derangement() {
    int k = 4;
    int[] derangements = new int[k + 1];
    derangements[0] = 1; // D(0) =
    if (k > 0)
      derangements[1] = 0; // D(1) =
    for (int i = 2; i <= k; i++) {
      derangements[i] = (i - 1) * (derangements[i - 1] + derangements[i - 2]);
    }
  }

  private static void SPF() {
    int N = 100;
    int[] spf = new int[N + 1];
    for (int i = 1; i <= N; i++) {
      spf[i] = i;
    }
    for (int i = 2; i * i <= N; i++) {
      if (spf[i] == i) {
        // this is the prime?
        for (int j = i * i; j <= N; j += i) {
          if (spf[j] == j) {
            // this number is not touched ever.
            spf[j] = i;
          }
        }
      }
    }
  }

  private static int phi(int n) {
    int result = n;
    for (int i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        while (n % i == 0) {
          n /= i;
        }
        result -= (result / i);
      }
    }
    if (n > 1) {
      result -= (result / n);
    }
    return result;
  }

  private static void addAllPrimFact(int x, HashMap<Integer, Integer> map) {
    int i = 2;
    while (i * i <= x) {
      while (x % i == 0) {
        map.put(i, map.getOrDefault(i, 0) + 1);
        x /= i;
      }
      i++;
    }
    if (x > 1) {
      map.put(x, map.getOrDefault(x, 0) + 1);
    }
  }

  static boolean[] isPrime;
  static ArrayList<Integer> primes;

  public static void sieve(int n) {
    isPrime = new boolean[n + 1];
    primes = new ArrayList<>();
    Arrays.fill(isPrime, true);
    isPrime[0] = false;
    isPrime[1] = false;
    for (int i = 2; i * i <= n; i++) {
      if (isPrime[i]) {
        for (int j = i * i; j <= n; j += i) {
          isPrime[j] = false;
        }
      }
    }
    for (int i = 2; i <= n; i++) {
      if (isPrime[i]) {
        primes.add(i);
      }
    }
  }

  public static List<Boolean> segmentedSieveNoPreGen(long L, long R) {
    int size = (int) (R - L + 1);
    List<Boolean> isPrime = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      isPrime.add(true);
    }
    long lim = (long) Math.sqrt(R);
    for (long i = 2; i <= lim; i++) {
      long start = Math.max(i * i, (L + i - 1) / i * i);
      for (long j = start; j <= R; j += i) {
        isPrime.set((int) (j - L), false);
      }
    }
    if (L == 1)
      isPrime.set(0, false);
    return isPrime;
  }

  // Find primes in range
  public static List<Boolean> segmentedSieve(long L, long R) {
    long lim = (long) Math.sqrt(R);
    boolean[] mark = new boolean[(int) (lim + 1)];
    List<Long> primes = new ArrayList<>();
    for (long i = 2; i <= lim; i++) {
      if (!mark[(int) i]) {
        primes.add(i);
        for (long j = i * i; j <= lim; j += i) {
          mark[(int) j] = true;
        }
      }
    }
    List<Boolean> isPrime = new ArrayList<>();
    for (int i = 0; i <= R - L; i++) {
      isPrime.add(true);
    }
    for (long prime : primes) {
      long start = Math.max(prime * prime, (L + prime - 1) / prime * prime);
      for (long j = start; j <= R; j += prime) {
        isPrime.set((int) (j - L), false);
      }
    }
    if (L == 1) {
      isPrime.set(0, false);
    }
    return isPrime;
  }

  public static int countPrimes(int n) {
    final int S = 10000;
    int nsqrt = (int) Math.sqrt(n);
    List<Integer> primes = new ArrayList<>();
    boolean[] isPrime = new boolean[nsqrt + 1];
    Arrays.fill(isPrime, true);
    for (int i = 2; i <= nsqrt; i++) {
      if (isPrime[i]) {
        primes.add(i);
        for (int j = i * i; j <= nsqrt; j += i) {
          isPrime[j] = false;
        }
      }
    }
    int result = 0;
    boolean[] block = new boolean[S];
    for (int k = 0; k * S <= n; k++) {
      Arrays.fill(block, true);
      int start = k * S;
      for (int p : primes) {
        int startIdx = Math.max((start + p - 1) / p, p);
        int j = startIdx * p - start;
        for (; j < S; j += p) {
          block[j] = false;
        }
      }
      if (k == 0) {
        block[0] = block[1] = false;
      }
      for (int i = 0; i < S && start + i <= n; i++) {
        if (block[i]) {
          result++;
        }
      }
    }
    return result;
  }

  public static long factorial(long minOp) {
    long fact = 1;
    for (int i = 2; i <= minOp; i++) {
      fact = (fact * i);
    }
    return fact;
  }

  // to check in arr[i] the j- th bit set or not.
  // if((arr[i]&(1<<j))!=0) {
  // count++; this means the jth bit is set.increase count
  // }
  // int bit = (num >> i) & 1;

  int flipBit(int n, int j) {
    return n ^ (1 << j);
  } // note: if we add 2^(x-1) to num then num will not divisibe by that x again.

  // mex calculate for the arr of permutation
  // long mex = (n * (n + 1) / 2) - sum;

  private static int computeXOR(int n) {
    if (n % 4 == 0)
      return n;
    if (n % 4 == 1)
      return 1;
    if (n % 4 == 2)
      return n + 1;
    return 0;
  }

  public static int findMSB(long n) {
    int msb = 0;
    while (n > 1) {
      n >>= 1;
      msb++;
    }
    return 1 << msb;
  }

  private static void reverse(int[] array, long left, long right) {
    while (left < right) {
      long temp = array[(int) left];
      array[(int) left] = array[(int) right];
      array[(int) right] = (int) temp;
      left++;
      right--;
    }
  }

  private static long rangeSum(long l, long r) {
    if (l > r) {
      return 0;
    }
    return (l + r) * (r - l + 1) / 2;
  }

  public static long gcd(long a, long b) {
    if (a == 0)
      return b;
    return gcd(b % a, a);
  }

  public static void factor(long n) {
    long count = 0;
    for (int i = 1; i * i <= n; i++) {
      if (n % i == 0) {
        // i -> is the one factor
        count++;
        if (i != n / i) {
          // n / i -> is the other factor
          count++;
        }
      }
    }
  }

  private static int getPrime(int n) {
    while (n % 2 == 0)
      return 2;
    for (int i = 3; i <= Math.sqrt(n); i += 2) {
      while (n % i == 0)
        return i;
    }
    if (n > 2)
      return n;
    return n;
  }

  public static boolean isPrime(long arr) {
    if (arr <= 1)
      return false;
    for (int i = 2; i <= Math.sqrt(arr); i++) {
      if (arr % i == 0)
        return false;
    }
    return true;
  }

  private static void swap(long[] arr, long i, long j) {
    long t = arr[(int) i];
    arr[(int) i] = arr[(int) j];
    arr[(int) j] = t;
  }

  public static long MahantaDist(long x1, long y1, long x2, long y2) {
    return Math.abs(x1 - x2) + Math.abs(y1 - y2);
  }

  public static long numberOfDivisors(long num) {
    long total = 1;
    for (long i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        int e = 0;
        while (num % i == 0) {
          e++;
          num /= i;
        }
        total *= (e + 1);
      }
    }
    if (num > 1) {
      total *= 2;
    }
    return total;
  }

  public static long sumOfDivisors(long num) {
    long total = 1;
    for (long i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        int e = 0;
        while (num % i == 0) {
          e++;
          num /= i;
        }
        long sum = 0, pow = 1;
        while (e-- >= 0) {
          sum += pow;
          pow *= i;
        }
        total *= sum;
      }
    }
    if (num > 1) {
      total *= (1 + num);
    }
    return total;
  }

  public static long lcm(long a, long b) {
    return Math.abs(a * b) / gcd(a, b);
  }

  // long[] fact = new long[n + 2];
  // long[] ifact = new long[n + 2];
  // fact[0] = 1;
  // for (int i = 1; i <= n + 1; i++) {
  // fact[i] = (fact[i - 1] * i) % MOD;
  // }

  // ifact[n + 1] = binPow(fact[n + 1], MOD - 2, MOD);
  // for (int i = n; i >= 0; i--) {
  // ifact[i] = (ifact[i + 1] * (i + 1)) % MOD;
  // }

  static long combination(long n, long r, long[] fact, long[] ifact) {
    if (r > n || r < 0)
      return 0;
    return ((fact[(int) n] * ifact[(int) r]) % MOD * ifact[(int) (n - r)] % MOD) % MOD;
  }

  // This is used when we use Pair inside the map
  Map<Pair, Integer> map = new HashMap<>();

  static class Pair {
    long first, second;

    Pair(long first, long second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (o == null || getClass() != o.getClass())
        return false;
      Pair pair = (Pair) o;
      return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
      return (int) (31 * first + second);
    }
  }

  // Method to generate the next lexicographical permutation
  public static boolean nextPermutation(char[] array) {
    int n = array.length;
    int i = n - 2;
    while (i >= 0 && array[i] >= array[i + 1]) {
      i--;
    }
    if (i < 0) {
      return false;
    }
    int j = n - 1;
    while (array[j] <= array[i]) {
      j--;
    }
    swap2(array, i, j);
    reverse2(array, i + 1, n - 1);
    return true;
  }

  private static void reverse2(char[] array, int start, int end) {
    while (start < end) {
      swap2(array, start, end);
      start++;
      end--;
    }
  }

  private static void swap2(char[] array, int i, int j) {
    char temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  private static long calculateDigitSum(int n) {
    // to calculate the sum (1 + 2 + .... )
    // (each digit it replaced by there sum of the digit).

    long sum = 0;
    int factor = 1;
    int leftOver = 0;

    // Process each digit position
    while (n > 0) {
      int digit = n % 10;
      int higher = n / 10;
      sum += higher * factor * 45; // Sum of all digits from 0 to 9 is 45
      sum += digit * (digit - 1) / 2 * factor; // Sum of digits within the current group
      sum += digit * leftOver; // Adjust for digits already processed
      leftOver += digit * factor; // Update leftover for next digit position
      factor *= 10;
      n /= 10;
    }
    return sum;
  }

  /*---------------------------------------------------------------------------------------- */

  // TREES
  private static void dfs(int node, List<List<Integer>> edges, int parent, int[] subtreeSize) {
    // subtreeSize[x] = 1 + sum(subtreeSize[child])
    subtreeSize[node] = 1;
    for (int neighbour : edges.get(node)) {
      if (neighbour != parent) {
        dfs(neighbour, edges, node, subtreeSize);
        // subtreeSize of neighbour child is added.
        subtreeSize[node] += subtreeSize[neighbour];
      }
    }
    // once we move out of the dfs call, the subtreeSize of node is correctly
    // populated
  }

  private static void dfs2(int node, List<List<Integer>> edges, int parent, int[] level) {
    if (parent == -1) {
      level[node] = 1;
    } else {
      level[node] = level[parent] + 1;
    }
    for (int neighbour : edges.get(parent))
      if (neighbour != parent)
        dfs(neighbour, edges, node, level);
  }

  /*--------------------------------------------------------------------------------------------- */
  // GRAPH
  static class Pair implements Comparable<Pair> {
    int node, weight;

    Pair(int node, int weight) {
      this.node = node;
      this.weight = weight;
    }

    public int compareTo(Pair other) {
      return this.weight - other.weight;
    }
  }

  static int[] dijkstra(List<List<Pair>> graph, int src, int n) {
    PriorityQueue<Pair> pq = new PriorityQueue<>();
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;
    pq.add(new Pair(src, 0));

    while (!pq.isEmpty()) {
      Pair p = pq.poll();
      int u = p.node;
      for (Pair neighbor : graph.get(u)) {
        int v = neighbor.node, weight = neighbor.weight;
        if (dist[u] + weight < dist[v]) {
          dist[v] = dist[u] + weight;
          pq.add(new Pair(v, dist[v]));
        }
      }
    }
    return dist;
  }

  static void bfs(List<List<Integer>> graph, int start) {
    int n = graph.size();
    boolean[] vis = new boolean[n];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    vis[start] = true;

    while (!q.isEmpty()) {
      int node = q.poll();
      // System.out.print(node + " ");
      for (int neighbor : graph.get(node)) {
        if (!vis[neighbor]) {
          vis[neighbor] = true;
          q.add(neighbor);
        }
      }
    }
  }

  static void dfs(List<List<Integer>> graph, int node, boolean[] vis) {
    vis[node] = true;
    System.out.print(node + " ");
    for (int neighbor : graph.get(node)) {
      if (!vis[neighbor]) {
        dfs(graph, neighbor, vis);
      }
    }
  }

  // static final int INF = 1_000_000_000;
  static void floydWarshall(int[][] dist, int n) {
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][k] < INF && dist[k][j] < INF)
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
  }

  // TOPOSORT
  public static boolean dfs(int node, int[] used, List<List<Integer>> adj, List<Integer> ans) {
    used[node] = 1; // in stack
    for (int adjNode : adj.get(node)) {
      if (used[adjNode] == 1) {
        // detected a cycle
        return false;
      } else if (used[adjNode] == 0) {
        // not visited
        if (!dfs(adjNode, used, adj, ans)) {
          return false;
        }
      }
    }
    used[node] = 2; // visited but out of stack
    ans.add(node);
    return true;
  }

  public static boolean dfsCycleDG(int node, List<List<Integer>> adj, boolean[] visited, boolean[] onStack) {
    visited[node] = true;
    onStack[node] = true;

    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        if (dfsCycleDG(neighbor, adj, visited, onStack))
          return true;
      } else if (onStack[neighbor]) {
        return true; // Cycle detected
      }
    }

    onStack[node] = false;
    return false;
  }

  public static boolean hasCycle(int n, List<List<Integer>> adj) {
    int[] inDegree = new int[n];
    for (int u = 0; u < n; u++) {
      for (int v : adj.get(u))
        inDegree[v]++;
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 0)
        q.add(i);
    }

    int count = 0;
    while (!q.isEmpty()) {
      int u = q.poll();
      count++;
      for (int v : adj.get(u)) {
        if (--inDegree[v] == 0)
          q.add(v);
      }
    }

    return count != n; // If count < n, there is a cycle
  }

  public static List<Integer> topoSortDfs(int n, List<List<Integer>> adj) {
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
      if (!visited[i])
        dfsTopo(i, adj, visited, stack);
    }

    List<Integer> topo = new ArrayList<>();
    while (!stack.isEmpty())
      topo.add(stack.pop());
    return topo;
  }

  public static void dfsTopo(int node, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;
    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        dfsTopo(neighbor, adj, visited, stack);
      }
    }
    stack.push(node);
  }

  public static List<Integer> topoSortBFS(int n, List<List<Integer>> adj) {
    int[] inDegree = new int[n];
    for (int u = 0; u < n; u++) {
      for (int v : adj.get(u)) {
        inDegree[v]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 0)
        q.add(i);
    }

    List<Integer> topo = new ArrayList<>();
    while (!q.isEmpty()) {
      int u = q.poll();
      topo.add(u);
      for (int v : adj.get(u)) {
        if (--inDegree[v] == 0)
          q.add(v);
      }
    }

    return topo.size() == n ? topo : new ArrayList<>(); // Return empty if cycle exists
  }

}

/*---------------------------------------------------------------------------------- */
// GRAPH

// DSU (both by size and by rank)
class DSU {
  private int[] parent, rank, size;

  public DSU(int n) {
    parent = new int[n];
    rank = new int[n];
    size = new int[n]; //
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;//
    }
  }

  public int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public boolean union(int u, int v) {
    int rootU = find(u);
    int rootV = find(v);
    if (rootU == rootV)
      return false;

    if (rank[rootU] > rank[rootV]) {
      parent[rootV] = rootU;
      size[rootU] += size[rootV];//
    } else if (rank[rootU] < rank[rootV]) {
      parent[rootU] = rootV;
      size[rootV] += size[rootU];//
    } else {
      parent[rootV] = rootU;
      rank[rootU]++;
      size[rootU] += size[rootV];//
    }
    return true;
  }

  //
  public int getSize(int x) {
    return size[find(x)];
  }
}