import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

public class AnimationLoader
{
	Hashtable<String, ArrayList<AnimationData>>	data	  = new Hashtable<String, ArrayList<AnimationData>>();
	
	public AnimationLoader(URL url)
	{
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String cur_line;
		try {
			while ((cur_line = in.readLine()) != null) {
				String line = cur_line.trim();
				if (line.equals("") || line.contains("#"))
					continue;
				String[] split = line.split(" ");
				if (!data.containsKey(split[0])) {
					data.put(split[0], new ArrayList<AnimationData>());
				}
				float encodedTime = Float.valueOf(split[1]);
				int startTime = (int) Math.floor(encodedTime);
				int endTime = (int) (encodedTime * 100 - startTime * 100);
				int dx = Integer.parseInt(split[2]);
				int dy = Integer.parseInt(split[3]);
				data.get(split[0])
						.add(new AnimationData(startTime, endTime, dx, dy));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[] nextFrame(String name, int current_frame)
	{
		ArrayList<AnimationData> search = data.get(name);
		int[] deltas = { 0, 0 };
		for (AnimationData a : search) {
			if (a.start <= current_frame && a.stop > current_frame) {
				deltas[0] = a.dx;
				deltas[1] = a.dy;
				return deltas;
			}
		}
		return deltas;
	}
	
	class AnimationData
	{
		int	start;
		int	stop;
		int	dx;
		int	dy;
		
		public AnimationData(int start, int stop, int dx, int dy)
		{
			this.start = start;
			this.stop = stop;
			this.dx = dx;
			this.dy = dy;
		}
	}
}
